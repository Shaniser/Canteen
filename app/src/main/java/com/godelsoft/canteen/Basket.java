package com.godelsoft.canteen;

import java.util.HashMap;

/**
 * Класс, описывающий корзину
 */
public class Basket {
    private HashMap<Integer, Integer> idToCount = new HashMap<>();

    /**
     * Добавление еды в корзину
     * @param food - вид еды
     * @param count - количество
     */

    public void add(Food food, int count){
        if(idToCount.containsKey(food.getId())) {
            idToCount.put(food.getId(), idToCount.get(food.getId()) + count);
        }else{
            idToCount.put(food.getId(), count);
        }
    }

    /**
     * Добавление еды в корзину
     * @param id - id вида еды
     * @param count - количество
     */

    public void add(int id, int count){
        if(idToCount.containsKey(id)) {
            idToCount.put(id, idToCount.get(id) + count);
        }else{
            idToCount.put(id, count);
        }
    }
}
