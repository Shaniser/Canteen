package com.godelsoft.canteen;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Класс, описывающий столовую
 */
public class CanteenProvider {
    private String name;
    private TimeSpan[] workTime, breakTime; //Первый элемент - воскресение!!!
    private Menu[] menus;
    private ArrayList<Food> allDishes;

    /**
     * Создание нового объекта
     * @param data Строковое представление столовой и меню на текущую неделю
     */
    public CanteenProvider(String data) {
        this.workTime = new TimeSpan[7];
        this.breakTime = new TimeSpan[7];
        this.menus = new Menu[7];

        try {
            String[] arr = data.split("\n");
            this.name = arr[0];
            for (int i = 0; i < 7; i++) {
                if (arr[i + 2].equals("-"))
                    this.workTime[i] = null;
                else {
                    String[] tarr = arr[i + 2].split(":");
                    this.workTime[i] = new TimeSpan(Integer.parseInt(tarr[0]), Integer.parseInt(tarr[1]), Integer.parseInt(tarr[2]), Integer.parseInt(tarr[3]));
                }
                if (arr[i + 10].equals("-"))
                    this.breakTime[i] = null;
                else {
                    String[] tarr = arr[i + 2].split(":");
                    this.breakTime[i] = new TimeSpan(Integer.parseInt(tarr[0]), Integer.parseInt(tarr[1]), Integer.parseInt(tarr[2]), Integer.parseInt(tarr[3]));
                }
            }
            int ind = 18;
            allDishes = new ArrayList<>();
            while (!arr[ind].equals("END_DISHES")) {
                String[] tarr = arr[ind].split(":");
                allDishes.add(new Food(
                        Integer.parseInt(tarr[0]), Integer.parseInt(tarr[1]), tarr[2], Integer.parseInt(tarr[3]),
                        Double.parseDouble(tarr[4]), Double.parseDouble(tarr[5]), Double.parseDouble(tarr[6]),
                        Integer.parseInt(tarr[7]), tarr[8].equals("+"), Integer.parseInt(tarr[9])));
                ind++;
            }
            for (int i = 0; i < 7; i++) {
                if (arr[ind + i + 1].equals("-"))
                    this.menus[i] = null;
                else {
                    this.menus[i] = new Menu();
                    String[] tarr = arr[ind + i + 1].split(":");
                    for (String d : tarr)
                        this.menus[i].add(allDishes.get(Integer.parseInt(d)));
                }
            }
        } catch (Exception e) {
            //TODO
            throw new RuntimeException("Menu failed to load");
        }
    }


    /**
     * Получение меню на заданный день
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
     * Сообщает, работает ли столовая в заданный день в заданное время
     * @param day Момент времени
     * @return true - если столовая работает, false - иначе
     */
    public boolean isWorking(GregorianCalendar day) {
        if (this.workTime[day.get(Calendar.DAY_OF_WEEK) - 1] == null)
            return false;
        if (this.breakTime[day.get(Calendar.DAY_OF_WEEK) - 1] == null)
            return this.workTime[day.get(Calendar.DAY_OF_WEEK) - 1].isInInterval(day);
        else
            return this.workTime[day.get(Calendar.DAY_OF_WEEK) - 1].isInInterval(day) &&
                !this.breakTime[day.get(Calendar.DAY_OF_WEEK) - 1].isInInterval(day);
    }

    /**
     * Сообщает, работает ли столовая сейчас
     * @return true - если столовая работает, false - иначе
     */
    public boolean isWorking() {
        return isWorking(new GregorianCalendar());
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


    /**
     * Возвращает строку, пригодную для создания нового объекта CanteenProvider
     * @return Строка
     */
    @NonNull
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.name).append("\n\n");
        for (int i = 0; i < 7; i++)
            if (this.workTime[i] == null)
                builder.append("-\n");
            else
                builder.append(String.format(Locale.US, "%d:%d:%d:%d",
                        this.workTime[i].getOpenHour(), this.workTime[i].getOpenMin(),
                        this.workTime[i].getCloseHour(), this.workTime[i].getCloseMin()));
        builder.append('\n');
        for (int i = 0; i < 7; i++)
            if (this.breakTime[i] == null)
                builder.append("-\n");
            else
                builder.append(String.format(Locale.US, "%d:%d:%d:%d",
                        this.breakTime[i].getOpenHour(), this.breakTime[i].getOpenMin(),
                        this.breakTime[i].getCloseHour(), this.breakTime[i].getCloseMin()));
        builder.append("START_DISHES\n");
        for (Food i : this.allDishes)
            builder.append(i.toString()).append('\n');
        builder.append("END_DISHES\n");
        for (int i = 0; i < 7; i++)
            if (menus[i] == null)
                builder.append("-\n");
            else {
                ArrayList<Food> t = menus[i].getList();
                for (int j = 0; j < t.size() - 1; j++)
                    builder.append(t.get(j).getId()).append(':');
                builder.append(t.get(t.size() - 1)).append('\n');
            }
        return builder.toString();
    }
}
