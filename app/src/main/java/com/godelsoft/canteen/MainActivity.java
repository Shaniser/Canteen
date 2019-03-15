package com.godelsoft.canteen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Food testFood = new Food(1,3, "Гречка", 120, 0.55, 0.27, 0.29, 200, true, 10050);

        String data = "Столовая ГЗ\n" +
                "\n" +
                "-\n" +
                "10:00:18:00\n" +
                "10:00:18:00\n" +
                "10:00:18:00\n" +
                "10:00:18:00\n" +
                "10:00:18:00\n" +
                "-\n" +
                "\n" +
                "-\n" +
                "-\n" +
                "-\n" +
                "-\n" +
                "-\n" +
                "-\n" +
                "-\n" +
                "START_DISHES\n" +
                "0:0:Холодец:114:18.22:16.67:2.54:236:-:5000\n" +
                "1:1:Борщ:245:3.36:4.02:8.26:78:-:9000\n" +
                "2:1:Суп грибной:250:2.32:8.98:9.3:129:+:6500\n" +
                "3:2:Котлета домашняя:95:14.52:13.34:12.71:233:-:7500\n" +
                "4:2:Куриные голени запеченые:60:13.94:5.75:0:111:-:5500\n" +
                "5:3:Рис вареный:120:3.29:0.42:29.49:139:+:4000\n" +
                "6:3:Гречка отварная:115:3.7:4.8:21.91:138:+:6000\n" +
                "7:3:Картофель запеченный:120:3.13:2.99:17.61:108:+:4000\n" +
                "8:3:Красная фасоль:115:0.34:5.25:15.59:85:+:3000\n" +
                "9:4:Хлеб черный:26:1.72:0.31:11.29:52:+:1000\n" +
                "10:6:Чай черный:120:0:0:0.71:2:+:2000\n" +
                "END_DISHES\n" +
                "-\n" +
                "0:1:3:5:7:9:10\n" +
                "0:2:4:6:8:9:10\n" +
                "0:1:3:5:7:9:10\n" +
                "0:2:4:6:8:9:10\n" +
                "0:1:3:5:7:9:10\n" +
                "-\n";
        CanteenProvider provider = new CanteenProvider(data);

        boolean isWorking = provider.isWorking(new GregorianCalendar(2019, 2, 16, 15, 0));
        isWorking = provider.isWorking(new GregorianCalendar(2019, 2, 18, 23, 0));
        isWorking = provider.isWorking(new GregorianCalendar(2019, 2, 18, 15, 0));

        Intent intent = new Intent(this, AboutFood.class);
        intent.putExtra("id", testFood.getId());
        startActivity(intent);
    }

}
