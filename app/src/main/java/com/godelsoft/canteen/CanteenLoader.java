package com.godelsoft.canteen;

import android.content.res.AssetManager;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Класс, предоставляющий данные о столовых
 */
public class CanteenLoader {
    private AssetManager manager;
    private ArrayList<CanteenProvider> canteens;

    public CanteenLoader(AssetManager manager) {
        this.manager = manager;
        this.canteens = new ArrayList<>();
        String files[] = loadAssetData("menus/canteensList.txt").split("\n");
        for (String s : files)
            this.canteens.add(new CanteenProvider(loadAssetData(String.format("menus/%s", s))));
    }

    private String loadAssetData(String filepath) {
        byte buff[];
        try (InputStream is = this.manager.open(filepath)) {
            buff = new byte[is.available()];
            is.read(buff);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load asset");
        }
        return new String(buff);
    }

    /**
     * Получение списка всех столовых
     * @return Массив CanteenProvider
     */
    public CanteenProvider[] getCanteens() {
        CanteenProvider[] t = new CanteenProvider[this.canteens.size()];
        for (int i = 0; i < t.length; i++)
            t[i] = this.canteens.get(i);
        return t;
    }
}
