package com.godelsoft.canteen;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class AboutCanteen extends AppCompatActivity {
    static String[] sortFilters;
    static MenuFilter filter = new MenuFilter((Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + 5) % 7, 0, false);
    static CardView[] dayCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_canteen);

        int id = getIntent().getExtras().getInt("id");
        final CanteenProvider canteen = CanteenProvider.all.get(id);

        sortFilters = new String[] { getResources().getString(R.string.group_sort), getResources().getString(R.string.alphabet_sort), getResources().getString(R.string.cost_sort) + "▲", getResources().getString(R.string.cost_sort) + "▼", getResources().getString(R.string.calories_sort) + "▲", getResources().getString(R.string.calories_sort) + "▼" };

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, sortFilters);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.about_canteen));

        dayCards = new CardView[] {findViewById(R.id.dayCard), findViewById(R.id.dayCard1), findViewById(R.id.dayCard2), findViewById(R.id.dayCard3), findViewById(R.id.dayCard4), findViewById(R.id.dayCard5)};

        applyFilters((LinearLayout) findViewById(R.id.menuLinLay), canteen, this);

        final Context context = this;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                applyFilters((LinearLayout) findViewById(R.id.menuLinLay), canteen, context);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    public void applyFilters(final LinearLayout linearLayout, final CanteenProvider canteenProvider, final Context context){
        int currentDay = (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + 5) % 7;


        if(canteenProvider.getFoodList(filter.getDayOfWeek()) != null) {
            for (int i = 0; i < dayCards.length; i++) {
                if (filter.getDayOfWeek() == i) {
                    dayCards[i].setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    ((TextView) dayCards[i].findViewById(R.id.weekDay)).setTextColor(Color.parseColor("#ffffff"));
                } else {
                    if(canteenProvider.getFoodList(i) != null) {
                        if (i == currentDay) {
                            ((TextView) dayCards[i].findViewById(R.id.weekDay)).setTextColor(getResources().getColor(R.color.colorPrimary));
                        } else {
                            ((TextView) dayCards[i].findViewById(R.id.weekDay)).setTextColor(Color.parseColor("#737373"));
                        }
                    }else{
                        ((TextView) dayCards[i].findViewById(R.id.weekDay)).setTextColor(Color.parseColor("#e0554a"));
                    }
                    dayCards[i].setCardBackgroundColor(Color.parseColor("#ffffff"));
                }

                dayCards[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int dayIndex = 0;
                        for (; dayCards[dayIndex] != view && dayIndex < 7; dayIndex++) {
                        }
                        filter.setDayOfWeek(dayIndex);
                        applyFilters(linearLayout, canteenProvider, context);
                    }
                });


            }

            filter.setComparator(((Spinner) findViewById(R.id.spinner)).getSelectedItemPosition());

            canteenProvider.getFoodList(filter.getDayOfWeek()).toScreen(linearLayout, context, filter.getComparator(), filter.isGroups());
        }else{

            for (int i = 0; i < dayCards.length; i++) {
                if (canteenProvider.getFoodList(i) == null && filter.getDayOfWeek() == i) {
                    dayCards[i].setCardBackgroundColor(Color.parseColor("#e0554a"));
                    ((TextView) dayCards[i].findViewById(R.id.weekDay)).setTextColor(Color.parseColor("#ffffff"));
                } else {
                    if(canteenProvider.getFoodList(i) != null) {
                        if (i == currentDay) {
                            ((TextView) dayCards[i].findViewById(R.id.weekDay)).setTextColor(getResources().getColor(R.color.colorPrimary));
                        } else {
                            ((TextView) dayCards[i].findViewById(R.id.weekDay)).setTextColor(Color.parseColor("#737373"));
                        }
                    }else{
                        ((TextView) dayCards[i].findViewById(R.id.weekDay)).setTextColor(Color.parseColor("#e0554a"));
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

            TextView textView = new TextView(context);
            textView.setText(getResources().getString(R.string.canteen_not_working));
            textView.setTextSize(18);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            linearLayout.removeAllViews();
            linearLayout.addView(textView);
        }
    }
}
