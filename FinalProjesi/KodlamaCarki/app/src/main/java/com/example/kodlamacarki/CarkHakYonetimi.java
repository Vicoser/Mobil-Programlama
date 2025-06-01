package com.example.kodlamacarki;

import android.content.Context;
import android.content.SharedPreferences;

public class CarkHakYonetimi {

    private static final String PREF_ADI = "CarkHaklarPrefs";
    private static final String ANAHTAR_HAK = "hakSayisi";
    private static final String ANAHTAR_TARIH = "sonSifirlama";

    private static final int MAKS_HAK = 3;
    private static final long YIRMIDORT_SAAT_MS = 24 * 60 * 60 * 1000;

    public static int hakGetir(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_ADI, Context.MODE_PRIVATE);
        long sonZaman = prefs.getLong(ANAHTAR_TARIH, 0);
        long simdi = System.currentTimeMillis();

        if (simdi - sonZaman >= YIRMIDORT_SAAT_MS) {
            prefs.edit()
                    .putInt(ANAHTAR_HAK, MAKS_HAK)
                    .putLong(ANAHTAR_TARIH, simdi)
                    .apply();
            return MAKS_HAK;
        }

        return prefs.getInt(ANAHTAR_HAK, MAKS_HAK);
    }

    public static void hakAzalt(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_ADI, Context.MODE_PRIVATE);
        int hak = hakGetir(context);
        if (hak > 0) {
            prefs.edit().putInt(ANAHTAR_HAK, hak - 1).apply();
        }
    }

    public static void hakSifirlaManuel(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_ADI, Context.MODE_PRIVATE);
        prefs.edit()
                .putInt(ANAHTAR_HAK, MAKS_HAK)
                .putLong(ANAHTAR_TARIH, System.currentTimeMillis())
                .apply();
    }
    public static long getSonHakTarihi(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_ADI, Context.MODE_PRIVATE);
        return prefs.getLong(ANAHTAR_TARIH, 0);
    }
    public static void haklariSifirlaVeVer(Context context, int hakSayisi) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_ADI, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(ANAHTAR_HAK, hakSayisi);
        editor.putLong(ANAHTAR_TARIH, System.currentTimeMillis());
        editor.apply();
    }

}
