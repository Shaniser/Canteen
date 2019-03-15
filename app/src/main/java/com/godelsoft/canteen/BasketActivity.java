package com.godelsoft.canteen;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Set;

public class BasketActivity extends AppCompatActivity {
    TextView  description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getResources().getString(R.string.basket));


        Menu basketMenu = new Menu();
        int count = 0, weight = 0, calories = 0, cost = 0, gramFats = 0, gramCarbo = 0, gramProt = 0;
        Set<Integer> foodSet = Basket.getIdSet();
        for (int id : foodSet){
            if(Basket.getCount(id) > 0) {
                Food food = Food.all.get(id);
                basketMenu.add(food);
                count += Basket.getCount(id);
                gramFats += food.getGramFats() * Basket.getCount(id);
                gramCarbo += food.getGramCarbohydrates() * Basket.getCount(id);
                gramProt += food.getGramProteins() * Basket.getCount(id);
                weight += food.getWeight();
                calories += food.getCalories();
            }
        }

        description = findViewById(R.id.description);
        StringBuilder builder = new StringBuilder();
        builder.append(getResources().getString(R.string.count));
        builder.append(" " + count + "\n");
        builder.append(getResources().getString(R.string.weight));
        builder.append(" " + weight + getResources().getString(R.string.gram) + "\n");
        builder.append(getResources().getString(R.string.calories));
        builder.append(" " + calories + getResources().getString(R.string.ccal) + "\n");
        builder.append(getResources().getString(R.string.cost));
        builder.append(" " + (cost / 100) + (((cost % 100) == 0) ? "" : "." + (cost % 100)) + getResources().getString(R.string.rub));
        description.setText(builder);
    }
}
