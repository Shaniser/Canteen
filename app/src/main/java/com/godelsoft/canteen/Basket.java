package com.godelsoft.canteen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Класс, описывающий корзину
 */
public final class Basket {
    private static HashMap<Integer, Integer> idToCount = new HashMap<>();

    /**
     * Добавление еды в корзину (не больше 99)
     * @param id - id вида еды
     * @param count - количество
     */
    public static void add(int id, int count){
        if(idToCount.containsKey(id)) {
            idToCount.put(id, Math.min(idToCount.get(id) + count, 99));
        }else{
            idToCount.put(id, count);
        }
        TextListener.change(id, idToCount.get(id));
        BasketActivity.countDescription(BasketActivity.currentBasketActivity);
    }

    public static void add(Food food, int count){
        add(food.getId(), count);
    }

    /**
     * Удаляет count единиц еды id из корзины
     * @param id
     * @param count
     * @return Произошло ли удаление
     */
    public static void remove(int id, int count){
        if(idToCount.containsKey(id) && idToCount.get(id) > 0) {
            idToCount.put(id, Math.max(idToCount.get(id) - count, 0));
            TextListener.change(id, idToCount.get(id));
        }else{
            TextListener.change(id, 0);
        }
        BasketActivity.countDescription(BasketActivity.currentBasketActivity);
    }

    public static void remove(Food food, int count){
        remove(food.getId(), count);
    }

    /**
     * Возвращает количество еды id в корзине
     * @param id
     * @return
     */
    public static int getCount(int id){
        if(!idToCount.containsKey(id)) return 0;
        return idToCount.get(id);
    }

    /**
     * Очистка корзины
     */
    public static void clear(){
        for(int id : idToCount.keySet()){
            idToCount.put(id, 0);
            TextListener.change(id, 0);
        }
        BasketActivity.countDescription(BasketActivity.currentBasketActivity);
    }

    public static Set<Integer> getIdSet(){
        return idToCount.keySet();
    }
}
