package com.godelsoft.canteen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Класс, описывающий столовую
 */
public class CanteenProvider {
    static SparseArray<CanteenProvider> all = new SparseArray<>();
    static int currentId;
    private int id;

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
        this.id = currentId++;
        all.put(id, this);
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
        return isWorking(Calendar.getInstance());
    }


    /**
     * Получение времени работы столовой (без перерыва)
     * @param weekDay Номер дня недели (Начиная с нуля и понедельника)
     * @return Время работы или null если в этот день не работает
     */
    public TimeSpan getWorkingTime(int weekDay) {
        if (weekDay < 5)
            return this.workTimeDef;
        else if (weekDay == 6)
            return this.workTimeSat;
        else
            return null;
    }
    /**
     * Получение времени перерыва столовой
     * @param weekDay Номер дня недели (Начиная с нуля и понедельника)
     * @return Время перерыва или null если работает без перерыва
     */
    public TimeSpan getBreakTime(int weekDay) {
        if (weekDay < 5)
            return this.breakTimeDef;
        else if (weekDay == 6)
            return this.breakTimeSat;
        else
            return null;
    }

    /**
     * Получение названия столовой
     * @return Название
     */
    public String getName() { return this.name; }

    /**
     * Создаёт карточку из объекта Canteen
     * @return View
     */
    public View toCard(final Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.canteen_card, null);
        TextView label = view.findViewById(R.id.label);
        TextView isOpened = view.findViewById(R.id.isOpened);

        label.setText(getName());
        if(isWorking()){
            isOpened.setText(context.getResources().getString(R.string.now_opened));
            isOpened.setTextColor(Color.parseColor("#0CB267"));
        }else{
            isOpened.setText(context.getResources().getString(R.string.now_closed));
            isOpened.setTextColor(Color.parseColor("#e0554a"));
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AboutCanteen.class);
                intent.putExtra("id", id);
                context.startActivity(intent);
            }
        });

        return view;
    }

    public int getId() {
        return id;
    }
}
