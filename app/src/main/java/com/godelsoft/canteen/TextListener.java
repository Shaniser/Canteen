package com.godelsoft.canteen;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

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

    public static void change(int listenId, String text){
        ArrayList<TextListener> listeners = TextListener.listeners.get(listenId);
        for(TextListener listener : listeners){
            listener.setText(text);
        }
    }

    public void setText(String text){
        textView.setText(text);
    }
}
