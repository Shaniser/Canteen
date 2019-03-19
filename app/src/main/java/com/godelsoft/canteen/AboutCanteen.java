package com.godelsoft.canteen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Класс страницы "О столовой"
 */
public class AboutCanteen extends AppCompatActivity {
    static String[] sortFilters;
    private MenuFilter filter = new MenuFilter((Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + 5) % 7, 0, false);
    static CardView[] dayCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_canteen);

        //Настройка шапки
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getResources().getString(R.string.about_canteen));

        //Получаем столовую по id
        int id = getIntent().getExtras().getInt("id");
        final CanteenProvider canteen = CanteenProvider.all.get(id);

        //Выводим название столовой
        ((TextView) findViewById(R.id.header)).setText(canteen.getName());

        //Выводим открыта столовая или нет
        TextView isOpened = findViewById(R.id.isOpened);
        if(canteen.isWorking()){
            isOpened.setText(getResources().getString(R.string.now_opened));
            isOpened.setTextColor(Color.parseColor("#0CB267"));
        }else{
            isOpened.setText(getResources().getString(R.string.now_closed));
            isOpened.setTextColor(Color.parseColor("#e0554a"));
        }

        //Формируем карточку с расписанием столовой
        TextView workingTime = findViewById(R.id.workingTime);
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s\n", canteen.getWorkingTime(0)));
        TimeSpan breakTime = canteen.getBreakTime(0);
        if(breakTime != null){
            builder.append(String.format("%s", breakTime));
        }else{
            builder.append(String.format("%s", getResources().getString(R.string.no)));
        }

        TimeSpan saturdayWorking = canteen.getWorkingTime(5);
        if(saturdayWorking != null){
            builder.append(String.format("\n%s\n", saturdayWorking));
            TimeSpan saturdayBreak = canteen.getBreakTime(5);
            if(saturdayBreak != null){
                builder.append(String.format("%s\n", saturdayBreak));
            }else{
                builder.append(String.format("%s", getResources().getString(R.string.no)));
            }
        }else{
            ((TextView) findViewById(R.id.workingTimeDescription)).setText(getResources().getString(R.string.working_time_description_no_saturday));
        }
        workingTime.setText(builder);

        //Создаем выпадающий список фильтров
        sortFilters = new String[] { getResources().getString(R.string.group_sort), getResources().getString(R.string.alphabet_sort), getResources().getString(R.string.cost_sort) + "▲", getResources().getString(R.string.cost_sort) + "▼", getResources().getString(R.string.calories_sort) + "▲", getResources().getString(R.string.calories_sort) + "▼" };
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, sortFilters);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Получаем кнопки, отвечающие за рабочие дни столовой
        if(saturdayWorking != null){
            dayCards = new CardView[] {findViewById(R.id.dayCard), findViewById(R.id.dayCard1), findViewById(R.id.dayCard2), findViewById(R.id.dayCard3), findViewById(R.id.dayCard4), findViewById(R.id.dayCard5)};
        }else{
            dayCards = new CardView[] {findViewById(R.id.dayCard), findViewById(R.id.dayCard1), findViewById(R.id.dayCard2), findViewById(R.id.dayCard3), findViewById(R.id.dayCard4)};
            ((TableRow) findViewById(R.id.days)).removeView(findViewById(R.id.dayCard5));
        }

        //Применияем фильтры
        applyFilters((LinearLayout) findViewById(R.id.menuLinLay), canteen, this);

        //Устанавливаем слушатель на выпадающий список
        final Context context = this;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                applyFilters((LinearLayout) findViewById(R.id.menuLinLay), canteen, context);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

        //Устанавливаем слушатель на чекбокс вегитерианской еды
        ((CheckBox) findViewById(R.id.isVegetarian)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                filter.setVegetarian(b);
                applyFilters((LinearLayout) findViewById(R.id.menuLinLay), canteen, context);
            }
        });
    }

    /**
     * Применение фильтров и изменение их отображения
     * @param linearLayout
     * @param canteenProvider
     * @param context
     */
    public void applyFilters(final LinearLayout linearLayout, final CanteenProvider canteenProvider, final Context context){
        //Текущий день недели (с 0)
        int currentDay = (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + 5) % 7;

        //Установка слушателей и настройка внешнего вида карточек-фильтров-по-дням-недели
        if(canteenProvider.getFoodList(filter.getDayOfWeek()) != null) {
            for (int i = 0; i < dayCards.length; i++) {
                if (filter.getDayOfWeek() == i) {
                    dayCards[i].setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    ((TextView) dayCards[i].findViewById(R.id.weekDay)).setTextColor(Color.parseColor("#ffffff"));
                } else {
                        if (i == currentDay) {
                            ((TextView) dayCards[i].findViewById(R.id.weekDay)).setTextColor(getResources().getColor(R.color.colorPrimary));
                        } else {
                            ((TextView) dayCards[i].findViewById(R.id.weekDay)).setTextColor(Color.parseColor("#737373"));
                        }
                    dayCards[i].setCardBackgroundColor(Color.parseColor("#ffffff"));
                }

                dayCards[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int dayIndex = 0;
                        for (; dayCards[dayIndex] != view; dayIndex++) {
                        }
                        filter.setDayOfWeek(dayIndex);
                        applyFilters(linearLayout, canteenProvider, context);
                    }
                });
            }

            filter.setComparator(((Spinner) findViewById(R.id.spinner)).getSelectedItemPosition());

            canteenProvider.getFoodList(filter.getDayOfWeek()).toScreen(linearLayout, context, filter.getComparator(), filter.isGroups(), filter.getIsVegetarian());
        }else{

            for (int i = 0; i < dayCards.length; i++) {
                if (i == currentDay) {
                      ((TextView) dayCards[i].findViewById(R.id.weekDay)).setTextColor(getResources().getColor(R.color.colorPrimary));
                } else {
                       ((TextView) dayCards[i].findViewById(R.id.weekDay)).setTextColor(Color.parseColor("#737373"));
                }
                dayCards[i].setCardBackgroundColor(Color.parseColor("#ffffff"));


                dayCards[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int dayIndex = 0;
                        for (; dayCards[dayIndex] != view; dayIndex++) {
                        }
                        filter.setDayOfWeek(dayIndex);
                        applyFilters(linearLayout, canteenProvider, context);
                    }
                });
            }

            TextView textView = new TextView(context);
            textView.setText(getResources().getString(R.string.canteen_not_working));
            textView.setTextSize(18);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            linearLayout.removeAllViews();
            linearLayout.addView(textView);
        }
    }

    /**
     * Настройка шапки
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
