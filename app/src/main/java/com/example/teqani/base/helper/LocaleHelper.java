package com.example.teqani.base.helper;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.view.View;

import com.example.teqani.base.data.Constants;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;
import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class LocaleHelper {
    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";
    private static LocaleHelper instance;
    SharedPreferences sharedPreferences;
    Context context;

    private LocaleHelper(Context context) {
        sharedPreferences = getDefaultSharedPreferences(context);
        this.context = context;
    }

    public static LocaleHelper getInstance(Context context) {
        if (instance == null) {
            instance = new LocaleHelper(context);
        }
        return instance;
    }

    private static Context updateResources(Context context, String country, String language) {
        Locale locale = new Locale(language, country);
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT >= 17) {
            if (Build.VERSION.SDK_INT >= 24) {

                LocaleList localeList = new LocaleList(locale);
                LocaleList.setDefault(localeList);
                config.setLocales(localeList);

                config.setLocale(locale);
                context = context.createConfigurationContext(config);
            } else {
                config.setLocale(locale);
                context = context.createConfigurationContext(config);
            }

        } else {
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
        return context;
    }

    public static String getSystemLanguage() {
        return Resources.getSystem().getConfiguration().locale.getLanguage();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint("NewApi")
    public static void ChangeDesignToRTL(Activity activity) {

        String Lang = getDefaultSharedPreferences(activity).getString(Constants.APP_LANGUAGE, "ar");

        // This code change the design to support arabic lang.
        if (Lang != null && Lang.equals("ar")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity.getWindow().getDecorView()
                        .getLayoutDirection() == View.LAYOUT_DIRECTION_LTR) {
                    activity.getWindow().getDecorView()
                            .setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                }
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint("NewApi")
    public static void ChangeDesignToLTR(Activity activity) {

        String Lang = getDefaultSharedPreferences(activity).getString(Constants.APP_LANGUAGE, "ar");

        // This code change the design to support arabic lang.
        if (Lang != null && Lang.equals("en")) {
            // This code change the design to support arabic lang.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {

                if (activity.getWindow().getDecorView().getLayoutDirection()
                        == View.LAYOUT_DIRECTION_RTL) {

                    activity.getWindow().getDecorView()
                            .setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

                }
            }
        }
    }

    public static boolean isRTL() {
        return isRTL(Locale.getDefault());
    }

    private static boolean isRTL(Locale locale) {
        final int directionality = Character.getDirectionality(locale.getDisplayName().charAt(0));
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
                directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
    }

    public String getLanguage() {

        return sharedPreferences.getString(Constants.APP_LANGUAGE, "ar");
    }

    public Context setLocale(Context context, String country, String language) {

        if (language.equals("ar"))
            changeLanguage("ar");
        else
            changeLanguage("en");
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context = updateResources(context, country, language);
        } else {
            updateResources(country, language);
        }
        return context;
    }

    public void changeLanguage(String language) {
        sharedPreferences.edit().putString(Constants.APP_LANGUAGE, language).apply();
    }

    private void updateResources(String country, String language) {
        Locale locale = new Locale(language, country);
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();
//        configuration.locale = locale;
        configuration.setLocale(locale);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            Locale l = Locale.getDefault();
            configuration.setLayoutDirection(Locale.getDefault());
        }
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }
}


