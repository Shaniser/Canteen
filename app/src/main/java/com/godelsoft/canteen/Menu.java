package com.godelsoft.canteen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Класс, описывающий содержимое меню столовой
 */
public class Menu {
    private ArrayList<Food> menu = new ArrayList<>(); //Список всех продуктов в меню для этой столовой
    private HashMap<Integer, ArrayList<Food>> typeToListFood = new HashMap<>(); //Списки продуктов по категориям type

    /**
     * Конструктор по умолчанию
     */
    public Menu() {
    }

    /**
     * Добавления блюда в меню
     * @param food Добавляемое блюдо
     */
    public void add(Food food) {
        menu.add(food);
        if(!typeToListFood.containsKey(food.getType())){
            typeToListFood.put(food.getType(), new ArrayList<Food>());
        }
        typeToListFood.get(food.getType()).add(food);
    }

    /**
     * Получение списка всех блюд
     * @return Список блюд
     */
    public ArrayList<Food> getList() { return this.menu; }

    /**
     * Выводит на экран все отсортированное меню, включая заголовки
     * @param ll - LinearLayout куда выводим
     * @param context Контекст
     * @param comparator - Критерий сортировки
     */
    public void toScreen(LinearLayout ll, Context context, Comparator<Food> comparator, boolean isGroups, boolean vegetarianOnly){
        ll.removeAllViews();

        if(isGroups) {
            for (int i = 0; i < Food.getTypesCount(); i++) {
                if (typeToListFood.containsKey(i)) {
                    boolean containsVegetarian = false;
                    if(vegetarianOnly){
                        for (Food food : typeToListFood.get(i)) {
                            containsVegetarian |= food.isVegetarian();
                        }
                    }

                    if(!vegetarianOnly || containsVegetarian){
                        View header = LayoutInflater.from(context).inflate(R.layout.header, null);
                        TextView h = header.findViewById(R.id.compHeader);
                        h.setText(Food.typeNames[i]);
                        ll.addView(header);
                    }

                    Collections.sort(typeToListFood.get(i), comparator);
                    for (Food food : typeToListFood.get(i)) {
                        if(!vegetarianOnly || food.isVegetarian()){
                            ll.addView(food.toCard(context));
                        }
                    }
                }
            }
        }else{
            Collections.sort(menu, comparator);
            for (Food food : menu) {
                if(!vegetarianOnly || food.isVegetarian()){
                    ll.addView(food.toCard(context));
                }
            }
        }
    }
}
