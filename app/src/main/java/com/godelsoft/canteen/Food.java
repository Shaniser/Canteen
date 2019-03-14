package com.godelsoft.canteen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Food {
    public static int SOUP = 0, SALAD = 1, PORRIDGE = 2,  MEAT = 3, FISH = 4, DESSERT = 5, DRINK = 6;
    public static String[] TYPES = { "Супы", "Салаты", "Каши", "Мясо", "Рыба", "Напитки", "Дессерты" };
    public static HashMap<Integer, Food> all = new HashMap<>();

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

    public int getId() {
        return id;
    }

    public void setCost(int newCost){
        this.cost = newCost;
    }

    public View toCard(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.food_card, null);
        ImageView mIcon = view.findViewById(R.id.foodTypeIcon);
        TextView mLabel = view.findViewById(R.id.label);
        TextView mWeigth = view.findViewById(R.id.weigth);
        TextView mCost = view.findViewById(R.id.cost);

        mLabel.setText(label);
        mWeigth.setText(weight);
        mCost.setText(cost);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }


    public int getType() {
        return type;
    }
}
