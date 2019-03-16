package com.godelsoft.canteen;

import java.util.ArrayList;
import java.util.Comparator;

public class MenuFilter {
    static ArrayList<Comparator<Food>> comparators = new ArrayList<Comparator<Food>>() {{
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

    public void setDayOfWeek(int dayOfWeek){
        this.dayOfWeek = dayOfWeek;
    }

    public void setVegetarian(boolean vegan){
        this.isVegan = vegan;
    }

    public void setComparator(int comparator){
        this.comparator = comparators.get(comparator);
        this.isGroups = comparator == 0;
    }

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
