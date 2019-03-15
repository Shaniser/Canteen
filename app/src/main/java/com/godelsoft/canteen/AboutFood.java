package com.godelsoft.canteen;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Arrays;

public class AboutFood extends AppCompatActivity {
    Food food;
    Button minus, plus;
    TextView countInBasket, label, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_food);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getResources().getString(R.string.about_food_title));

        label = findViewById(R.id.label);

        //TODO Формирование страницы с блюдом
        int id = getIntent().getExtras().getInt("id");
        food = Food.all.get(id);

        label.setText(food.getLabel());

        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        countInBasket = findViewById(R.id.count);
        countInBasket.setText("" + Basket.getCount(food.getId()));

        /**
         * Основное описание
         */
        description = findViewById(R.id.description);
        StringBuilder builder = new StringBuilder();
        builder.append(getResources().getString(R.string.category));
        builder.append(" " + Food.TYPES[food.getType()] + "\n");
        builder.append(getResources().getString(R.string.weight));
        builder.append(" " + food.getWeight() + getResources().getString(R.string.gram) + "\n");
        builder.append(getResources().getString(R.string.calories));
        builder.append(" " + food.getCalories() / 1000 + getResources().getString(R.string.ccal) + "\n");
        builder.append(getResources().getString(R.string.cost));
        builder.append(" " + (food.getCost() / 100) + (((food.getCost() % 100) == 0) ? "" : "." + (food.getCost() % 100)) + getResources().getString(R.string.rub));
        description.setText(builder);

        /**
         * Белки, жиры и углеводы
         */
        TextView proteinsP = findViewById(R.id.proteinsP);
        ProgressBar progressBarProteins = findViewById(R.id.proteinsProgressBar);
        progressBarProteins.setProgress((int)(food.getProteins() * 100));
        proteinsP.setText((int)(food.getProteins() * 100) + "%");
        TextView fatsP = findViewById(R.id.fatsP);
        ProgressBar progressBarFats = findViewById(R.id.fatsProgressBar);
        progressBarFats.setProgress((int)(food.getFats() * 100));
        fatsP.setText((int)(food.getFats() * 100) + "%");
        TextView carbohydratesP = findViewById(R.id.carbohydratesP);
        ProgressBar progressBarCarbohydrates = findViewById(R.id.carbohydratesProgressBar);
        progressBarCarbohydrates.setProgress((int)(food.getCarbohydrates() * 100));
        carbohydratesP.setText((int)(food.getCarbohydrates() * 100) + "%");

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Basket.remove(food, 1);
                countInBasket.setText("" + Basket.getCount(food.getId()));
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Basket.add(food, 1);
                countInBasket.setText("" + Basket.getCount(food.getId()));
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
