package com.godelsoft.canteen;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс, созданый для изменения текста в момент изменения количества определенных блюд в корзине
 */
public class TextListener {
    private static HashMap<Integer, ArrayList<TextListener>> listeners = new HashMap<>();
    public TextView textView;

    public TextListener(TextView textView, int listenId){
        this.textView = textView;
         if(!listeners.containsKey(listenId) || listeners.get(listenId) == null){
             listeners.put(listenId, new ArrayList<TextListener>());
         }
         listeners.get(listenId).add(this);
    }

    /**
     * Изменяет текст на всех объектах TextListener
     * @param listenId
     * @param count
     */
    public static void change(int listenId, int count){
        ArrayList<TextListener> listeners = TextListener.listeners.get(listenId);
        for(TextListener listener : listeners){
            listener.set(count);
        }
    }

    /**
     * Устанавливает текст на текущем TextListener
     * @param count
     */
    public void set(int count){
        textView.setText("" + count);
    }
}
