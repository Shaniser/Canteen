package com.godelsoft.canteen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Comparator;
import java.util.Set;

public class BasketActivity extends AppCompatActivity {
    static BasketActivity currentBasketActivity;
    TextView description, emptyBasket;
    ScrollView scrollView;
    Menu basketMenu;
    LinearLayout basketLinLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        currentBasketActivity = this;

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getResources().getString(R.string.basket));

        basketLinLay = findViewById(R.id.basketLinLay);

        countDescription();
    }

    public static void countDescription(){
        if(currentBasketActivity == null) return;
        currentBasketActivity.basketMenu = new Menu();

        /**
         * Подсчёт суммы
         */
        int count = 0, weight = 0, calories = 0, cost = 0, gramFats = 0, gramCarbo = 0, gramProt = 0;
        Set<Integer> foodSet = Basket.getIdSet();
        for (int id : foodSet){
            if(Basket.getCount(id) > 0) {
                Food food = Food.all.get(id);
                currentBasketActivity.basketMenu.add(food);
                count += Basket.getCount(id);
                gramFats += food.getGramFats() * Basket.getCount(id);
                gramCarbo += food.getGramCarbohydrates() * Basket.getCount(id);
                gramProt += food.getGramProteins() * Basket.getCount(id);
                weight += food.getWeight() * Basket.getCount(id);
                calories += food.getCalories() * Basket.getCount(id);
                cost += food.getCost() * Basket.getCount(id);
            }
        }

        if(count == 0){
            currentBasketActivity.findViewById(R.id.emptyBasket).setVisibility(View.VISIBLE);
            currentBasketActivity.findViewById(R.id.scrollView).setVisibility(View.GONE);
        }else{
            currentBasketActivity.findViewById(R.id.scrollView).setVisibility(View.VISIBLE);
            currentBasketActivity.findViewById(R.id.emptyBasket).setVisibility(View.GONE);
        }

        /**
         * Формирование описания
         */
        currentBasketActivity.description = currentBasketActivity.findViewById(R.id.description);
        StringBuilder builder = new StringBuilder();
        builder.append(" " + count + "\n");
        builder.append(" " + weight + currentBasketActivity.getResources().getString(R.string.gram) + "\n");
        builder.append(" " + calories + currentBasketActivity.getResources().getString(R.string.ccal) + "\n");
        builder.append(" " + (cost / 100) + (((cost % 100) == 0) ? "" : "." + (cost % 100)) + currentBasketActivity.getResources().getString(R.string.rub));
        currentBasketActivity.description.setText(builder);

        /**
         * Белки, жиры и углеводы
         */
        double prot = (double) gramProt / (gramCarbo + gramFats + gramProt), fats = (double) gramFats / (gramCarbo + gramFats + gramProt), carb = (double) gramCarbo / (gramCarbo + gramFats + gramProt);
        TextView proteinsP = currentBasketActivity.findViewById(R.id.proteinsP);
        ProgressBar progressBarProteins = currentBasketActivity.findViewById(R.id.proteinsProgressBar);
        progressBarProteins.setProgress((int)(prot * 100));
        proteinsP.setText(((double)(int)(prot * 10000) / 100) + "%");
        TextView fatsP = currentBasketActivity.findViewById(R.id.fatsP);
        ProgressBar progressBarFats = currentBasketActivity.findViewById(R.id.fatsProgressBar);
        progressBarFats.setProgress((int)(fats * 100));
        fatsP.setText(((double)(int)(fats * 10000) / 100) + "%");
        TextView carbohydratesP = currentBasketActivity.findViewById(R.id.carbohydratesP);
        ProgressBar progressBarCarbohydrates = currentBasketActivity.findViewById(R.id.carbohydratesProgressBar);
        progressBarCarbohydrates.setProgress((int)(carb * 100));
        carbohydratesP.setText(((double)(int)(carb * 10000) / 100) + "%");

        /**
         * Вывод содержимого корзины
         */
        currentBasketActivity.basketMenu.toScreen(currentBasketActivity.basketLinLay, currentBasketActivity, new Comparator<Food>() {
            @Override
            public int compare(Food food, Food t1) {
                return food.getLabel().compareTo(t1.getLabel());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
