package com.godelsoft.canteen;

import java.util.HashMap;

/**
 * Класс, описывающий корзину
 */
public final class Basket {
    private static HashMap<Integer, Integer> idToCount = new HashMap<>();

    /**
     * Добавление еды в корзину (не больше 99)
     * @param food - вид еды
     * @param count - количество
     */

    public static void add(Food food, int count){
        add(food.getId(), count);
    }

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
    }

    /**
     * Удаляет count единиц еды id из корзины
     * @param id
     * @param count
     * @return Произошло ли удаление
     */

    public static boolean remove(int id, int count){
        if(idToCount.containsKey(id) && idToCount.get(id) > 0) {
            idToCount.put(id, Math.max(idToCount.get(id) - count, 0));
            return true;
        }else{
            return false;
        }
    }

    /**
     * Удаляет count единиц еды food из корзины
     * @param food
     * @param count
     * @return Произошло ли удаление
     */

    public static boolean remove(Food food, int count){
        return remove(food.getId(), count);
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
}
