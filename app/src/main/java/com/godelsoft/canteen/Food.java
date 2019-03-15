package com.godelsoft.canteen;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.HashMap;

/**
 * Класс блюда
 */

public class Food {
    public static String[] TYPES = { "Закуски", "Первое", "Второе", "Гарнир", "Хлебобулочные изделия", "Десерты", "Напитки" };
    public static SparseArray<Food> all = new SparseArray<>(); //Полный список еды <ID, Food>

    private String label;
    private int id;
    private double proteins, fats, carbohydrates, calories;
    private boolean vegetarian;
    private int type, weight, cost;

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
     * @param newCost
     */
    public void setCost(int newCost){
        this.cost = newCost;
    }

    /**
     * Создаёт карточку из объекта Food
     * @param context
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


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Переход в подробную карточку
            }
        });

        return view;
    }

    public int getId() {
        return id;
    }


    public int getType() {
        return type;
    }
}
