package com.godelsoft.canteen;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс, описывающий столовую
 */
public class CanteenProvider {
    private TimeSpan[] workTime, breakTime; //Первый элемент - воскресение!!!

    /**
     * Создание нового объекта
     * @param data Строковое представление столовой и меню на текущую неделю
     */
    public CanteenProvider(String data) {
        this.workTime = new TimeSpan[7]; //По умолчанию все дни - выходные
        this.breakTime = new TimeSpan[7];
        //TODO
    }

    /**
     * Получение списка блюд, продаваемых в столовой в заданный день
     * @param day День
     * @return Список блюд
     */
    public List<Food> getFoodList(GregorianCalendar day) {
        //TODO
        return null;
    }

    /**
     * Получение списка блюд, продаваемых в столовой в текущий день
     * @return Список блюд
     */
    public List<Food> getFoodList() {
        return getFoodList(new GregorianCalendar());
    }

    /**
     * Получение времени работы столовой (без перерыва)
     * @param day Заданный день
     * @return Время работы или null если в этот день не работает
     */
    public TimeSpan getWorkingTime(GregorianCalendar day) { return this.workTime[day.get(Calendar.DAY_OF_WEEK) - 1]; }
    /**
     * Получение времени перерыва столовой
     * @param day Заданный день
     * @return Время перерыва или null если работает без перерыва
     */
    public TimeSpan getBreakTime(GregorianCalendar day) { return this.breakTime[day.get(Calendar.DAY_OF_WEEK) - 1]; }

    /**
     * Получение времени работы столовой (без перерыва) в текущий день
     * @return Время работы или null если в этот день не работает
     */
    public TimeSpan getWorkingTime() { return getWorkingTime(new GregorianCalendar()); }
    /**
     * Получение времени перерыва столовой в текущий день
     * @return Время перерыва или null если работает без перерыва
     */
    public TimeSpan getBreakTime() { return getBreakTime(new GregorianCalendar()); }


    public String toString() {
        //TODO
        return null;
    }
}
