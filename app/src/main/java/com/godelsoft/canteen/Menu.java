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
     * Конструктор, из массива id заполняет menu и typeToListFood
     * @param ids Массив id
     */
    public Menu(int[] ids){
        for(int id : ids){
            Food food = Food.all.get(id);
            menu.add(food);
            if(!typeToListFood.containsKey(food.getType())){
                typeToListFood.put(food.getType(), new ArrayList<Food>());
            }
            typeToListFood.get(food.getType()).add(food);
        }
    }

    /**
     * Конструктор, из массива блюд заполняет menu и typeToListFood
     * @param foods Массив блюд
     */
    public Menu(ArrayList<Food> foods){
        for(Food food : foods){
            menu.add(food);
            if(!typeToListFood.containsKey(food.getType())){
                typeToListFood.put(food.getType(), new ArrayList<Food>());
            }
            typeToListFood.get(food.getType()).add(food);
        }
    }

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
    public void toScreen(LinearLayout ll, Context context, Comparator<Food> comparator){
        for (int i = 0; i < Food.TYPES.length; i++){
            if(typeToListFood.containsKey(i)){
                View header = LayoutInflater.from(context).inflate(R.layout.header, null);
                TextView h = header.findViewById(R.id.header);
                h.setText(Food.TYPES[i]);
                ll.addView(h);

                Collections.sort(typeToListFood.get(i), comparator);
                for(Food food : typeToListFood.get(i)){
                    ll.addView(food.toCard(context));
                }
            }
        }
    }
}
