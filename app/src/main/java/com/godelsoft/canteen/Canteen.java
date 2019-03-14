package com.godelsoft.canteen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Canteen {
    private ArrayList<Food> menu = new ArrayList<>();
    private HashMap<Integer, ArrayList<Food>> typeToListFood = new HashMap<>();

    public Canteen(int[] ids){
        for(int id : ids){
            Food food = Food.all.get(id);
            menu.add(food);
            if(!typeToListFood.containsKey(food.getType())){
                typeToListFood.put(food.getType(), new ArrayList<Food>());
            }
            typeToListFood.get(food.getType()).add(food);
        }
    }

    public Canteen(ArrayList<Food> ids){
        for(Food food : ids){
            menu.add(food);
            if(!typeToListFood.containsKey(food.getType())){
                typeToListFood.put(food.getType(), new ArrayList<Food>());
            }
            typeToListFood.get(food.getType()).add(food);
        }
    }

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
