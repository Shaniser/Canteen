package com.godelsoft.canteen;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Класс описывающий критерии сортировки и вывода меню на экран
 */
public class MenuFilter {
    static ArrayList<Comparator<Food>> comparators = new ArrayList<Comparator<Food>>() {{ //Компораторы для сортировки карточек с едой
        add(new Comparator<Food>() {
            @Override
            public int compare(Food food, Food t1) {
                return food.getLabel().compareTo(t1.getLabel());
            }
        });

        add(new Comparator<Food>() {
            @Override
            public int compare(Food food, Food t1) {
                return food.getLabel().compareTo(t1.getLabel());
            }
        });

        add(new Comparator<Food>() {
            @Override
            public int compare(Food food, Food t1) {
                return food.getCost() - t1.getCost();
            }
        });

        add(new Comparator<Food>() {
            @Override
            public int compare(Food food, Food t1) {
                return - food.getCost() + t1.getCost();
            }
        });

        add(new Comparator<Food>() {
            @Override
            public int compare(Food food, Food t1) {
                return food.getCalories() - t1.getCalories();
            }
        });

        add(new Comparator<Food>() {
            @Override
            public int compare(Food food, Food t1) {
                return - food.getCalories() + t1.getCalories();
            }
        });
    }};


    private Comparator<Food> comparator;
    private int dayOfWeek;
    private boolean isVegan;
    private boolean isGroups;

    public MenuFilter(int dayOfWeek, int comparator, boolean isVegan){
        this.dayOfWeek = dayOfWeek;
        this.comparator = comparators.get(comparator);
        this.isVegan = isVegan;
        this.isGroups = comparator == 0;
    }

    /**
     * Изменение дня недели
     * @param dayOfWeek
     */
    public void setDayOfWeek(int dayOfWeek){
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Установка флажка "Вегитерианские"
     * @param vegan
     */
    public void setVegetarian(boolean vegan){
        this.isVegan = vegan;
    }

    /**
     * Установка компоратора по номеру
     * @param comparator
     */
    public void setComparator(int comparator){
        this.comparator = comparators.get(comparator);
        this.isGroups = comparator == 0;
    }

    //Геттеры
    public Comparator<Food> getComparator(){
        return comparator;
    }

    public boolean getIsVegetarian(){
        return isVegan;
    }

    public int getDayOfWeek(){
        return dayOfWeek;
    }

    public boolean isGroups(){
        return isGroups;
    }
}
