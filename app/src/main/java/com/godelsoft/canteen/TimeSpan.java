package com.godelsoft.canteen;

import java.util.GregorianCalendar;

/**
 * Класс промежутка времени работы или перерыва
 */
public class TimeSpan {
    private GregorianCalendar open, close;

    /**
     * Создание нового объекта
     * @param open Время открытия (Часы и минуты)
     * @param close Время закрытия (Часы и минуты)
     */
    public TimeSpan(GregorianCalendar open, GregorianCalendar close) {
        this.open = open;
        this.close = close;
    }

    /**
     * Получение времени открытия
     * @return Время открытия
     */
    public GregorianCalendar getOpenTime() { return this.open; }

    /**
     * Получение времени закрытия
     * @return Время закрытия
     */
    public GregorianCalendar getCloseTime() { return this.close; }

    /**
     * Проверка, входит ли данное время в промежуток
     * @param time Проверяемое время
     * @return Вхождение времени в промежуток
     */
    public boolean isInInterval(GregorianCalendar time) {
        return this.open.before(time) && this.close.after(time);
    }
}
