package com.godelsoft.canteen;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Locale;

/**
 * Класс промежутка времени работы или перерыва
 */
public class TimeSpan {
    private int openHour, openMin, closeHour, closeMin;

    /**
     * Создание нового объекта
     * @param openHour Час начала промежутка
     * @param openMin Минуты начала промежутка
     * @param closeHour Час конца промежутка
     * @param closeMin Минуты конца промежутка
     */
    public TimeSpan(int openHour, int openMin, int closeHour, int closeMin) {
        this.openHour = openHour;
        this.openMin = openMin;
        this.closeHour = closeHour;
        this.closeMin = closeMin;
    }

    /**
     * Создание нового объекта
     * @param str Строка в формате "%d:%d:%d:%d" или "-"
     * @return Новый объект или null
     */
    public static TimeSpan fromString(String str) {
        if (str.equals("-"))
            return null;
        else {
            String[] tarr = str.split(":");
            return new TimeSpan(Integer.parseInt(tarr[0]), Integer.parseInt(tarr[1]), Integer.parseInt(tarr[2]), Integer.parseInt(tarr[3]));
        }
    }

    /**
     * Получение часа начала промежутка
     * @return Час
     */
    public int getOpenHour() { return this.openHour; }

    /**
     * Получение минут начала промежутка
     * @return Минуты
     */
    public int getOpenMin() { return this.openMin; }

    /**
     * Получение часа конца промежутка
     * @return Час
     */
    public int getCloseHour() { return this.closeHour; }

    /**
     * Получение минут конца промежутка
     * @return Минуты
     */
    public int getCloseMin() { return this.closeMin; }

    /**
     * Проверка, входит ли данный момент времени в промежуток
     * @param time Момент вермени
     * @return Вхождение в промежуток
     */
    public boolean isInInterval(Calendar time) {
        return
                (this.openHour < time.get(Calendar.HOUR_OF_DAY) ||
                    (this.openHour == time.get(Calendar.HOUR_OF_DAY) && this.openMin <= time.get(Calendar.MINUTE))) &&
                (this.closeHour > time.get(Calendar.HOUR_OF_DAY) ||
                    (this.closeHour == time.get(Calendar.HOUR_OF_DAY) && this.closeMin >= time.get(Calendar.MINUTE)));
    }

    @NonNull
    public String toString() {
        return String.format(Locale.US, "%d:%d-%d:%d", this.openHour, this.openMin, this.closeHour, this.closeMin);
    }
}
