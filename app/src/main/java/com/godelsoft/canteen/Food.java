package com.godelsoft.canteen;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Locale;

/**
 * Класс блюда
 */

public class Food {
    public static String[] typeNames = { "Салаты", "Первые блюда", "Вторые блюда", "Гарниры", "Хлебобулочные изделия", "Десерты", "Напитки" };
    public static int[] typeImages = { R.drawable.salad, R.drawable.first, R.drawable.second_hot, R.drawable.garnish, R.drawable.bread, R.drawable.desert, R.drawable.drink };
    public static SparseArray<Food> all = new SparseArray<>(); //Полный список еды <ID, Food>

    private String label, description;
    private int id;
    private double proteins, fats, carbohydrates, gramProteins, gramFats, gramCarbohydrates;
    private boolean vegetarian;
    private int type, weight, calories, cost;

    public Food(int id, int type, String label, int weight, double proteins, double fats, double carbohydrates, int calories, boolean vegetarian, int costInKopecks, String description){
        this.id = id;
        this.type = type;
        this.label = label;
        this.weight = weight;
        this.gramProteins = proteins;
        this.gramFats = fats;
        this.gramCarbohydrates = carbohydrates;
        this.proteins = proteins / (proteins + fats + carbohydrates);
        this.fats = fats / (proteins + fats + carbohydrates);
        this.carbohydrates = carbohydrates / (proteins + fats + carbohydrates);
        this.vegetarian = vegetarian;
        this.cost = costInKopecks;
        this.calories = calories;
        this.description = description;

        //Добавляем блюдо в хеш таблицу для дальнейшего получения объекта Food по id
        all.put(id, this);
    }

    /**
     * Создаёт карточку из объекта Food
     * @param context Контекст
     * @return cardView
     */
    public View toCard(final Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.food_card, null);
        ImageView mIcon = view.findViewById(R.id.foodTypeIcon);
        TextView mLabel = view.findViewById(R.id.label);
        TextView mWeigth = view.findViewById(R.id.weigth);
        TextView mCost = view.findViewById(R.id.cost);
        TextView mCalories = view.findViewById(R.id.calories);
        mIcon.setImageResource(typeImages[type]);

        mLabel.setText(label);
        mWeigth.setText(String.format("%d%s", weight, context.getResources().getString(R.string.gram)));
        mCost.setText(String.format("%d%s%s", cost / 100, ((cost % 100) == 0) ? "" : "." + (cost % 100), context.getResources().getString(R.string.rub)));
        mCalories.setText(String.format("%d%s", calories, context.getResources().getString(R.string.ccal)));

        Button minus = view.findViewById(R.id.minus);
        Button plus = view.findViewById(R.id.plus);
        TextListener countInBasket = new TextListener((TextView) view.findViewById(R.id.count), id);
        countInBasket.set(Basket.getCount(id));

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Basket.remove(id, 1);
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Basket.add(id, 1);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AboutFood.class);
                intent.putExtra("id", id);
                context.startActivity(intent);
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

    public String getTypeName() {
        return Food.typeNames[this.type];
    }

    public static int getTypesCount() { return Food.typeNames.length; }

    //Getters
    public int getId() { return id; }
    public int getType() { return type; }
    public String getLabel() { return label; }
    public int getWeight(){ return weight; }
    public int getCalories() { return calories; }
    public int getCost() { return cost; }
    public double getProteins() { return proteins; }
    public double getFats() { return fats; }
    public double getCarbohydrates() { return carbohydrates; }
    public double getGramCarbohydrates() { return gramCarbohydrates; }
    public double getGramFats() { return gramFats; }
    public double getGramProteins() { return gramProteins; }
    public boolean isVegetarian(){
        return vegetarian;
    }
    public String getDescription() { return this.description; }

}
