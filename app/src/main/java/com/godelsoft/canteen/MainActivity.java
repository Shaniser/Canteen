package com.godelsoft.canteen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Comparator;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.canteens));
//        actionBar.setTitle("Canteens");

        mTextMessage = (TextView) findViewById(R.id.message);

        String data1 = "Столовая ГЗ\n" +
                "\n" +
                "10:0:18:0\n" +
                "-\n" +
                "\n" +
                "15:0:15:30\n" +
                "-\n" +
                "START_DISHES\n" +
                "0:0:Холодец:114:18.22:16.67:2.54:236:-:5000\n" +
                "1:1:Борщ:245:3.36:4.02:8.26:78:-:9000\n" +
                "2:1:Суп грибной:250:2.32:8.98:9.3:129:+:6500\n" +
                "3:2:Котлета домашняя:95:14.52:13.34:12.71:233:-:7500\n" +
                "4:2:Куриные голени запеченые:60:13.94:5.75:0:111:-:5500\n" +
                "5:3:Рис вареный:120:3.29:0.42:29.49:139:+:4000\n" +
                "6:3:Гречка отварная:115:3.7:4.8:21.91:138:+:6000\n" +
                "7:3:Картофель запеченный:125:3.13:2.99:17.61:108:+:4500\n" +
                "8:3:Красная фасоль:115:0.34:5.25:15.59:85:+:3000\n" +
                "9:4:Хлеб черный:26:1.72:0.31:11.29:52:+:1000\n" +
                "10:6:Чай черный:120:0:0:0.71:2:+:2000\n" +
                "END_DISHES\n" +
                "0:1:3:5:7:9:10\n" +
                "0:2:4:6:8:9:10\n" +
                "0:1:3:5:7:9:10\n" +
                "0:2:4:6:8:9:10\n" +
                "0:1:3:5:7:9:10\n" +
                "-\n" +
                "-\n";

        String data2 = "Столовая УЛК\n" +
                "\n" +
                "8:30:19:0\n" +
                "8:30:19:0\n" +
                "\n" +
                "-\n" +
                "-\n" +
                "START_DISHES\n" +
                "0:0:Сельдь под шубой:100:5.03:15.75:7.21:189:-:6000\n" +
                "1:0:Винигрет:80:0.77:8.36:5.35:98:-:5000\n" +
                "2:1:Куриный суп лапша:241:3.18:2.39:7.4:65:-:8000\n" +
                "3:1:Бульон с яйцом:190:5.64:2.87:0.82:55:-:6500\n" +
                "4:1:Суп рыбный (горбуша):250:8.83:2.72:6.39:86:-:6000\n" +
                "5:1:Борщ:245:3.36:4.02:8.26:78:-:9000\n" +
                "6:2:Котлета домашняя:95:14.52:13.34:12.71:233:-:7500\n" +
                "7:2:Сосиски молочные (2 штуки):120:13.19:33.57:0.86:360:-:6000\n" +
                "8:2:Рыба жареная (сайда):120:17.44:0.85:0:82:-:10000\n" +
                "9:2:Куриные голени запеченые:60:13.94:5.75:0:111:-:5500\n" +
                "10:2:Свинина тушеная:115:18.77:20.08:2.84:270:-:10000\n" +
                "11:3:Рис вареный:120:3.29:0.42:29.49:139:+:4000\n" +
                "12:3:Гречка отварная:115:3.7:4.8:21.91:138:+:6000\n" +
                "13:3:Картофель запеченный:120:3.13:2.99:17.61:108:+:4000\n" +
                "14:3:Пшеничная крупа булгур:100:12.29:1.33:75.87:342:+:4000\n" +
                "15:3:Красная фасоль:115:0.34:5.25:15.59:85:+:3000\n" +
                "16:4:Хлеб черный:26:1.72:0.31:11.29:52:+:1000\n" +
                "17:4:Хлеб белый:25:1.91:0.82:12.65:66:+:1000\n" +
                "18:6:Морс:120:1.29:0.62:11.35:50:+:3000\n" +
                "19:6:Чай черный:120:0:0:0.71:2:+:2000\n" +
                "20:6:Чай зеленый:120:0:0:0.71:2:+:2000\n" +
                "END_DISHES\n" +
                "0:1:2:3:4:5:6:7:8:9:10:11:12:13:14:15:16:17:18:19:20\n" +
                "0:1:2:3:4:5:6:7:8:9:10:11:12:13:14:15:16:17:18:19:20\n" +
                "0:1:2:3:4:5:6:7:8:9:10:11:12:13:14:15:16:17:18:19:20\n" +
                "0:1:2:3:4:5:6:7:8:9:10:11:12:13:14:15:16:17:18:19:20\n" +
                "0:1:2:3:4:5:6:7:8:9:10:11:12:13:14:15:16:17:18:19:20\n" +
                "0:1:2:3:4:5:6:7:8:9:10:11:12:13:14:15:16:17:18:19:20\n" +
                "0:1:2:3:4:5:6:7:8:9:10:11:12:13:14:15:16:17:18:19:20\n";

        CanteenProvider provider = new CanteenProvider(data1);

        Menu testMenu = provider.getFoodList(0);
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
