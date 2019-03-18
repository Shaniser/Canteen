package com.godelsoft.canteen;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AboutFood extends AppCompatActivity {
    Food food;
    Button minus, plus;
    TextView label, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_food);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getResources().getString(R.string.about_food_title));

        label = findViewById(R.id.label);

        int id = getIntent().getExtras().getInt("id");
        food = Food.all.get(id);

        label.setText(food.getLabel());

        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        TextListener countInBasket = new TextListener((TextView) findViewById(R.id.count), food.getId());
        countInBasket.set(Basket.getCount(food.getId()));

        ((ImageView)findViewById(R.id.icon)).setImageResource(Food.typeImages[food.getType()]);

        //Основное описание
        description = findViewById(R.id.description);
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(" %s\n", food.getTypeName()));
        builder.append(String.format(" %s%s\n", food.getWeight(), getResources().getString(R.string.gram)));
        builder.append(String.format(" %s%s\n", food.getCalories(), getResources().getString(R.string.ccal)));
        builder.append(String.format(" %s%s%s", food.getCost() / 100, (((food.getCost() % 100) == 0) ? "" : "." + (food.getCost() % 100)), getResources().getString(R.string.rub)));
        description.setText(builder);

        //Белки, жиры и углеводы
        TextView proteinsP = findViewById(R.id.proteinsP);
        ProgressBar progressBarProteins = findViewById(R.id.proteinsProgressBar);
        progressBarProteins.setProgress((int)(food.getProteins() * 100));
        proteinsP.setText(((double)(int)(food.getProteins() * 10000) / 100) + "%");
        TextView fatsP = findViewById(R.id.fatsP);
        ProgressBar progressBarFats = findViewById(R.id.fatsProgressBar);
        progressBarFats.setProgress((int)(food.getFats() * 100));
        fatsP.setText(((double)(int)(food.getFats() * 10000) / 100) + "%");
        TextView carbohydratesP = findViewById(R.id.carbohydratesP);
        ProgressBar progressBarCarbohydrates = findViewById(R.id.carbohydratesProgressBar);
        progressBarCarbohydrates.setProgress((int)(food.getCarbohydrates() * 100));
        carbohydratesP.setText(((double)(int)(food.getCarbohydrates() * 10000) / 100) + "%");

        TextListener totalCost = new TextListener((TextView) findViewById(R.id.totalCost), food.getId()){
            @Override
            public void set(int count){
                int cost = food.getCost() * count;
                textView.setText((cost / 100) + (((cost % 100) == 0) ? "" : "." + (cost % 100)) + getResources().getString(R.string.rub));
            }
        };

        totalCost.set(Basket.getCount(food.getId()));

        TextListener totalCalories = new TextListener((TextView) findViewById(R.id.totalCalories), food.getId()){
            @Override
            public void set(int count){
                double calories = food.getCalories() * count;
                textView.setText(String.format("%s%s", calories, getResources().getString(R.string.ccal)));
            }
        };

        totalCalories.set(Basket.getCount(food.getId()));

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Basket.remove(food, 1);
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Basket.add(food, 1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.basket:
                Intent intent = new Intent(this, BasketActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
