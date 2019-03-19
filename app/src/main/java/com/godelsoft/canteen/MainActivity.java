package com.godelsoft.canteen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.LinearLayout;

/**
 * Точка входа в приложение
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Настройка шапки
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.canteens));

        //Вывод списка столвых на экран
        LinearLayout linearLayout = findViewById(R.id.canteenLinLay);
        CanteenProvider[] canteens = new CanteenLoader(getAssets()).getCanteens();
        for (CanteenProvider canteenProvider : canteens){
            linearLayout.addView(canteenProvider.toCard(this));
        }

    }

    /**
     * Настройка шапки
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
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
            case R.id.basket:
                Intent intent = new Intent(this, BasketActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
