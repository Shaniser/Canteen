package com.godelsoft.canteen;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Класс промежутка времени работы или перерыва
 */
public class TimeSpan {
    private GregorianCalendar open, close;

    /**
     * Создание нового объекта
     * @param open Время начала промежутка (Часы и минуты)
     * @param close Время конца промежутка (Часы и минуты)
     */
    public TimeSpan(GregorianCalendar open, GregorianCalendar close) {
        this.open = open;
        this.close = close;
    }

    /**
     * Создание нового объекта
     * @param openHour Час начала промежутка
     * @param openMin Минуты начала промежутка
     * @param closeHour Час конца промежутка
     * @param closeMin Минуты конца промежутка
     */
    public TimeSpan(int openHour, int openMin, int closeHour, int closeMin) {
        this(new GregorianCalendar(0, 0, 0, openHour, openMin), new GregorianCalendar(0, 0, 0, closeHour, closeMin));
    }

    /**
     * Получение времени начала промежутка
     * @return Время
     */
    public GregorianCalendar getOpenTime() { return this.open; }

    /**
     * Получение времени конца промежутка
     * @return Время
     */
    public GregorianCalendar getCloseTime() { return this.close; }

    /**
     * Получение часа начала промежутка
     * @return Час
     */
    public int getOpenHour() { return this.getOpenTime().get(Calendar.HOUR); }

    /**
     * Получение минут начала промежутка
     * @return Минуты
     */
    public int getOpenMin() { return this.getOpenTime().get(Calendar.MINUTE); }

    /**
     * Получение часа конца промежутка
     * @return Час
     */
    public int getCloseHour() { return this.getCloseTime().get(Calendar.HOUR); }

    /**
     * Получение минут конца промежутка
     * @return Минуты
     */
    public int getCloseMin() { return this.getCloseTime().get(Calendar.MINUTE); }

    /**
     * Проверка, входит ли данный момент времени в промежуток
     * @param time Момент вермени
     * @return Вхождение в промежуток
     */
    public boolean isInInterval(GregorianCalendar time) {
        return this.open.before(time) && this.close.after(time);
    }
}
