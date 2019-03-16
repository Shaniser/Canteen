package com.godelsoft.canteen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.LinearLayout;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.canteens));

        CanteenProvider[] canteens = new CanteenLoader().getCanteens();
        Menu testMenu = canteens[0].getFoodList(0);

        testMenu.toScreen((LinearLayout)findViewById(R.id.menuTestLinLay), this, new Comparator<Food>() {
            @Override
            public int compare(Food food, Food t1) {
                return food.getCost() - t1.getCost();
            }
        }, true);

        Intent intent = new Intent(this, AboutCanteen.class);
        intent.putExtra("id", 0);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

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
