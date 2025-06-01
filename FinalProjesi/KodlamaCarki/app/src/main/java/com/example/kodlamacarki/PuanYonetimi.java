package com.example.kodlamacarki;

import android.content.Context;
import android.content.SharedPreferences;

public class PuanYonetimi {
    private static final String PREF_ADI = "KodlamaCarkiPuan";
    private static final String ANAHTAR_TOPLAM_PUAN = "toplamPuan";

    public static void puanEkle(Context context, int puan) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_ADI, Context.MODE_PRIVATE);
        int mevcutPuan = prefs.getInt(ANAHTAR_TOPLAM_PUAN, 0);
        prefs.edit().putInt(ANAHTAR_TOPLAM_PUAN, mevcutPuan + puan).apply();
    }

    public static int toplamPuaniGetir(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_ADI, Context.MODE_PRIVATE);
        return prefs.getInt(ANAHTAR_TOPLAM_PUAN, 0);
    }
}
