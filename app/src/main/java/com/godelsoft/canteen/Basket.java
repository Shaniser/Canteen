package com.godelsoft.canteen;

import java.util.HashMap;

public class Basket {
    private HashMap<Integer, Integer> idToCount = new HashMap<>();

    public void add(Food food, int count){
        if(idToCount.containsKey(food.getId())) {
            idToCount.put(food.getId(), idToCount.get(food.getId()) + count);
        }else{
            idToCount.put(food.getId(), count);
        }
    }

    public void add(int id, int count){
        if(idToCount.containsKey(id)) {
            idToCount.put(id, idToCount.get(id) + count);
        }else{
            idToCount.put(id, count);
        }
    }
}
