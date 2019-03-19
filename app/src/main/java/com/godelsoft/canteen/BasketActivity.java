package com.godelsoft.canteen;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Comparator;
import java.util.Locale;
import java.util.Set;

/**
 * Класс страницы с корзиной
 */
public class BasketActivity extends AppCompatActivity {
    static BasketActivity currentBasketActivity;
    String shareBasket;
    TextView description;
    Menu basketMenu;
    LinearLayout basketLinLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        currentBasketActivity = this;

        //Настройка шапки
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getResources().getString(R.string.basket));

        //Получения LinearLayout для вывода карточек с едой
        basketLinLay = findViewById(R.id.basketLinLay);

        //Установка обработчика нажатий на кнопку "очистить корзину"
        ((Button)findViewById(R.id.clearBasket)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Basket.clear();
            }
        });

        //Вызов функции пересчета содержимого корзины
        countDescription(currentBasketActivity);
    }

    /**
     * Пересчет текущего содержимого корзины
     * @param currentBasketActivity
     */
    public static void countDescription(BasketActivity currentBasketActivity){
        if(currentBasketActivity == null) return;
        currentBasketActivity.basketMenu = new Menu();

        //Подсчёт суммы и формирование спика покупок в shareBasket
        int count = 0, weight = 0, calories = 0, cost = 0;
        double gramFats = 0, gramCarbo = 0, gramProt = 0;
        Set<Integer> foodSet = Basket.getIdSet();
        StringBuilder shareBuilder = new StringBuilder();

        for (int id : foodSet) {
            if (Basket.getCount(id) > 0) {
                //Получаем блюдо по id
                Food food = Food.all.get(id);

                //Добавляем карточку с блюдом в список
                currentBasketActivity.basketMenu.add(food);

                //Добавляем блюдо в строку с покупками для функции share
                shareBuilder.append(food.getLabel());
                shareBuilder.append(" -");
                shareBuilder.append(String.format(" %d%s%s", food.getCost() / 100, ((food.getCost() % 100) == 0) ? "" : "." + (food.getCost() % 100), currentBasketActivity.getResources().getString(R.string.rub)));
                shareBuilder.append(" ");
                shareBuilder.append(Basket.getCount(id));
                shareBuilder.append(currentBasketActivity.getResources().getString(R.string.unit));
                shareBuilder.append("\n");

                //Подсчитываем суммарные параметры на всю корзину
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
            //Выводим надпись, что корзина пуста, статистику скрываем
            currentBasketActivity.findViewById(R.id.emptyBasket).setVisibility(View.VISIBLE);
            currentBasketActivity.findViewById(R.id.scrollView).setVisibility(View.GONE);
        }else{
            //Выводим статистику, скрываем надпись, что корзина пуста
            currentBasketActivity.findViewById(R.id.scrollView).setVisibility(View.VISIBLE);
            currentBasketActivity.findViewById(R.id.emptyBasket).setVisibility(View.GONE);

            //Добавляем общую стоимость в строку для функции share
            shareBuilder.append("\n");
            shareBuilder.append(currentBasketActivity.getResources().getString(R.string.total_cost));
            shareBuilder.append(" ");
            shareBuilder.append(String.format(" %d%s%s", cost / 100, ((cost % 100) == 0) ? "" : "." + (cost % 100), currentBasketActivity.getResources().getString(R.string.rub)));

        }

        //Сохраняем строку для функции share
        currentBasketActivity.shareBasket = shareBuilder.toString();

        //Формирование описания
        currentBasketActivity.description = currentBasketActivity.findViewById(R.id.description);
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(" %d\n", count));
        builder.append(String.format(" %d%s\n", weight, currentBasketActivity.getResources().getString(R.string.gram)));
        builder.append(String.format(" %d%s\n", calories, currentBasketActivity.getResources().getString(R.string.ccal)));
        builder.append(String.format(" %d%s%s", cost / 100, ((cost % 100) == 0) ? "" : "." + (cost % 100), currentBasketActivity.getResources().getString(R.string.rub)));
        currentBasketActivity.description.setText(builder);

        //Белки, жиры и углеводы (подсчет и заполнение в TextView)
        double prot = gramProt / (gramCarbo + gramFats + gramProt), fats = gramFats / (gramCarbo + gramFats + gramProt), carb = gramCarbo / (gramCarbo + gramFats + gramProt);
        TextView proteinsP = currentBasketActivity.findViewById(R.id.proteinsP);
        ProgressBar progressBarProteins = currentBasketActivity.findViewById(R.id.proteinsProgressBar);
        progressBarProteins.setProgress((int)(prot * 100));
        proteinsP.setText(String.format(Locale.US, "%.2f%%", prot * 100));
        TextView fatsP = currentBasketActivity.findViewById(R.id.fatsP);
        ProgressBar progressBarFats = currentBasketActivity.findViewById(R.id.fatsProgressBar);
        progressBarFats.setProgress((int)(fats * 100));
        fatsP.setText(String.format(Locale.US, "%.2f%%", fats * 100));
        TextView carbohydratesP = currentBasketActivity.findViewById(R.id.carbohydratesP);
        ProgressBar progressBarCarbohydrates = currentBasketActivity.findViewById(R.id.carbohydratesProgressBar);
        progressBarCarbohydrates.setProgress((int)(carb * 100));
        carbohydratesP.setText(String.format(Locale.US, "%.2f%%", carb * 100));

        //Вывод содержимого корзины
        currentBasketActivity.basketMenu.toScreen(currentBasketActivity.basketLinLay, currentBasketActivity, new Comparator<Food>() {
            @Override
            public int compare(Food food, Food t1) {
                return food.getLabel().compareTo(t1.getLabel());
            }
        }, false, false);
    }


    /**
     * Настройка шапки
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        return true;
    }


    /**
     * Обработка нажатия на элементы шапки
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.share:
                if(shareBasket != null && !shareBasket.isEmpty()){
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, shareBasket);
                    sendIntent.setType("text/plain");
                    startActivity(Intent.createChooser(sendIntent,getResources().getString(R.string.share)));
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.empty_basket), Toast.LENGTH_SHORT);
                    toast.show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
