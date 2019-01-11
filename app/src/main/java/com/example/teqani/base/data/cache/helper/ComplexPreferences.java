package com.example.teqani.base.data.cache.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.teqani.base.data.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ComplexPreferences {
    private Context context;
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private SharedPreferences.Editor editor;

    private static ComplexPreferences complexPreferences;

    public ComplexPreferences(Context context, String name, int mode) {
        this.context = context;
        gson = new Gson();

        if (name == null || name.isEmpty()) {
            name = "theComplexPref";
        }

        sharedPreferences = context.getSharedPreferences(name, mode);
        editor = sharedPreferences.edit();
    }

    public static ComplexPreferences getComplexPreferences(Context context, String name, int mode) {
        if (complexPreferences == null) {
            complexPreferences = new ComplexPreferences(context, name, mode);
        }

        return complexPreferences;
    }

    public void putObject(String key, Object object) {
        editor.putString(key, gson.toJson(object));
    }

    public void commmit() {
        editor.commit();
    }

    public void deleteObject(String key) {
        editor.remove(key);
    }

    public <T> T getObject(String key, Class<T> a) {
        String json = sharedPreferences.getString(key, null);
        if (json == null) {
            return null;
        } else {
            try {
                return gson.fromJson(json, a);
            } catch (Exception e) {
                throw new IllegalArgumentException("Object stored with key "
                        + key + " is instance of other class");
            }
        }
    }
}

