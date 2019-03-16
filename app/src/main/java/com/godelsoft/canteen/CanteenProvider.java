package com.godelsoft.canteen;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Класс, описывающий столовую
 */
public class CanteenProvider {
    static ArrayList<CanteenProvider> all = new ArrayList<>();
    private String name;
    private TimeSpan workTimeDef, breakTimeDef, workTimeSat, breakTimeSat; //Воскресение - выходной
    private Menu[] menus;
    private ArrayList<Food> allDishes;

    /**
     * Создание нового объекта
     * @param data Строковое представление столовой и меню на текущую неделю
     */
    public CanteenProvider(String data) {
        this.menus = new Menu[6];
        try {
            String[] arr = data.split("__END_LINE__\n");
            this.name = arr[0];

            this.workTimeDef = TimeSpan.fromString(arr[2]);
            this.workTimeSat = TimeSpan.fromString(arr[3]);
            this.breakTimeDef = TimeSpan.fromString(arr[5]);
            this.breakTimeSat = TimeSpan.fromString(arr[6]);

            int ind = 8;
            allDishes = new ArrayList<>();
            while (!arr[ind].equals("END_DISHES")) {
                String[] tarr = arr[ind].split(":");
                allDishes.add(new Food(
                        Integer.parseInt(tarr[0]), Integer.parseInt(tarr[1]), tarr[2], Integer.parseInt(tarr[3]),
                        Double.parseDouble(tarr[4]), Double.parseDouble(tarr[5]), Double.parseDouble(tarr[6]),
                        Integer.parseInt(tarr[7]), tarr[8].equals("+"), Integer.parseInt(tarr[9]),
                        arr[ind].substring(arr[ind].indexOf("\"") + 1, arr[ind].length() - 1)));
                ind++;
            }

            for (int i = 0; i < 6; i++) {
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
            throw new RuntimeException("Menu failed to load");
        }
        all.add(this);
    }

    /**
     * Получение меню в заданный день недели (
     * @param weekDay Номер дня недели (Начиная с нуля и понедельника)
     * @return Меню на заданный день недели
     */
    public Menu getFoodList(int weekDay) {
        if(weekDay == 6)
            return null;
        else
            return menus[weekDay]; //(day + 5) % 7
    }


    /**
     * Сообщает, работает ли столовая в заданный день в заданное время
     * @param d Момент времени
     * @return true - если столовая работает, false - иначе
     */
    public boolean isWorking(Calendar d) {
        int curWeekDay = (d.get(Calendar.DAY_OF_WEEK) - 1 + 5) % 7;
        if (curWeekDay < 5) { //Mon-Fri
            if (this.workTimeDef == null)
                return false;
            if (breakTimeDef == null)
                return this.workTimeDef.isInInterval(d);
            else
                return this.workTimeDef.isInInterval(d) &&
                        !this.breakTimeDef.isInInterval(d);
        }
        else { //Sat
            if (this.workTimeSat == null)
                return false;
            if (breakTimeSat == null)
                return this.workTimeSat.isInInterval(d);
            else
                return this.workTimeSat.isInInterval(d) &&
                        !this.breakTimeSat.isInInterval(d);
        }
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
     * @param weekDay Номер дня недели (Начиная с нуля и понедельника)
     * @return Время работы или null если в этот день не работает
     */
    public TimeSpan getWorkingTime(int weekDay) { return weekDay < 5 ? this.workTimeDef : this.workTimeSat; }
    /**
     * Получение времени перерыва столовой
     * @param weekDay Номер дня недели (Начиная с нуля и понедельника)
     * @return Время перерыва или null если работает без перерыва
     */
    public TimeSpan getBreakTime(int weekDay) { return weekDay < 5 ? this.breakTimeDef : this.breakTimeSat; }

    /**
     * Получение названия столовой
     * @return Название
     */
    public String getName() { return this.name; }


    /**
     * Возвращает имя столовой (DEPRECATED)
     * @return Имя
     */
    @Deprecated
    public String toString() {
//        StringBuilder builder = new StringBuilder();
//        builder.append(this.name).append("\n\n");
//
//        builder.append(String.format(Locale.US, "%d:%d:%d:%d",
//                this.workTimeDef.getOpenHour(), this.workTimeDef.getOpenMin(),
//                this.workTimeDef.getCloseHour(), this.workTimeDef.getCloseMin()));
//        builder.append(String.format(Locale.US, "%d:%d:%d:%d",
//                this.workTimeSat.getOpenHour(), this.workTimeSat.getOpenMin(),
//                this.workTimeSat.getCloseHour(), this.workTimeSat.getCloseMin()));
//        builder.append('\n');
//
//        builder.append(String.format(Locale.US, "%d:%d:%d:%d",
//                this.breakTimeDef.getOpenHour(), this.breakTimeDef.getOpenMin(),
//                this.breakTimeDef.getCloseHour(), this.breakTimeDef.getCloseMin()));
//        builder.append(String.format(Locale.US, "%d:%d:%d:%d",
//                this.breakTimeSat.getOpenHour(), this.breakTimeSat.getOpenMin(),
//                this.breakTimeSat.getCloseHour(), this.breakTimeSat.getCloseMin()));
//        builder.append('\n');
//
//        builder.append("START_DISHES\n");
//        for (Food i : this.allDishes)
//            builder.append(i.toString()).append('\n');
//        builder.append("END_DISHES\n");
//
//        for (int i = 0; i < 7; i++)
//            if (menus[i] == null)
//                builder.append("-\n");
//            else {
//                ArrayList<Food> t = menus[i].getList();
//                for (int j = 0; j < t.size() - 1; j++)
//                    builder.append(t.get(j).getId()).append(':');
//                builder.append(t.get(t.size() - 1)).append('\n');
//            }
//
//        return builder.toString();
        return this.name;
    }
}
