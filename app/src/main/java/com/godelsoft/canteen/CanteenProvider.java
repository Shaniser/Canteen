package com.godelsoft.canteen;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Класс, описывающий столовую
 */
public class CanteenProvider {
    private String name;
    private TimeSpan[] workTime, breakTime; //Первый элемент - воскресение!!!
    private Menu[] menus;

    /**
     * Создание нового объекта
     * @param data Строковое представление столовой и меню на текущую неделю
     */
    public CanteenProvider(String data) {
        this.workTime = new TimeSpan[7]; //По умолчанию все дни - выходные
        this.breakTime = new TimeSpan[7];
        this.menus = new Menu[7];
        //TODO parser

    }

    /**
     * Получение сменю на заданный день
     * @param day День
     * @return Список блюд
     */
    public Menu getFoodList(GregorianCalendar day) {
        return menus[day.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * Получение меню на текущий день
     * @return Список блюд
     */
    public Menu getFoodList() {
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

    /**
     * Получение названия столовой
     * @return Название
     */
    public String getName() { return this.name; }


    public String toString() {
        //TODO
        return null;
    }
}
