package com.godelsoft.canteen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Locale;

/**
 * Класс блюда
 */

public class Food {
    public static String[] TYPES = { "Закуски", "Первое", "Второе", "Гарнир", "Хлебобулочные изделия", "Десерты", "Напитки" };
    public static SparseArray<Food> all = new SparseArray<>(); //Полный список еды <ID, Food>

    private String label;
    private int id;
    private double proteins, fats, carbohydrates;
    private boolean vegetarian;
    private int type, weight, calories, cost;

    public Food(int id, int type, String label, int weight, double proteins, double fats, double carbohydrates, int calories, boolean vegetarian, int costInKopecks){
        this.id = id;
        this.type = type;
        this.label = label;
        this.weight = weight;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.vegetarian = vegetarian;
        this.cost = costInKopecks;
        this.calories = calories;

        all.put(id, this);
    }

    /**
     * Изменение стоимости
     * @param newCost Новая стоимость
     */
    public void setCost(int newCost){
        this.cost = newCost;
    }

    /**
     * Создаёт карточку из объекта Food
     * @param context Контекст
     * @return cardView
     */
    public View toCard(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.food_card, null);
        ImageView mIcon = view.findViewById(R.id.foodTypeIcon);
        TextView mLabel = view.findViewById(R.id.label);
        TextView mWeigth = view.findViewById(R.id.weigth);
        TextView mCost = view.findViewById(R.id.cost);
        //TODO Установить соответствующую блюду картинку

        mLabel.setText(label);
        mWeigth.setText(weight);
        mCost.setText(cost);

        Button minus = view.findViewById(R.id.minus);
        Button plus = view.findViewById(R.id.plus);
        TextView countInBasket = view.findViewById(R.id.count);

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Basket.remove(id, 1);
                //TODO View parent = view.;
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Переход в подробную карточку
            }
        });

        return view;
    }

    /**
     * Возвращает строку, представляющую объект в виде, подходящем для парсинга в CanteenProvider
     * @return Строковое представление блюда
     */
    @NonNull
    public String toString() {
        return String.format(Locale.US, "%d:%d:%s:%d:%f:%f:%f:%d:%c:%d",
                this.id, this.type, this.label, this.weight, this.proteins, this.fats, this.carbohydrates,
                this.calories, this.vegetarian ? '+' : '-', this.cost);
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public String getLabel() {
        return label;
    }

    public int getWeight(){
        return weight;
    }

    public int getCalories() {
        return calories;
    }

    public int getCost() {
        return cost;
    }

    public double getProteins() {
        return proteins;
    }

    public double getFats() {
        return fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }
}
