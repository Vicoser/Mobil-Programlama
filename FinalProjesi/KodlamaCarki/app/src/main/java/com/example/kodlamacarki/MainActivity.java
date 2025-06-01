package com.example.kodlamacarki;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textKalanSure;
    private TextView textKalanHak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // GEÇİCİ OLARAK: Her uygulama açılışında hakları sıfırla ve 3 yap
        //CarkHakYonetimi.haklariSifirlaVeVer(this, 3);





        // Hak ve süre göstergeleri
        textKalanSure = findViewById(R.id.textViewKalanSure);
        textKalanHak = findViewById(R.id.textKalanHak);

        // Geri sayımı başlat
        startTimer();


        Button btnCarkiCevir = findViewById(R.id.button_CarkiCevir);
        Button btnLiderlikTablosu = findViewById(R.id.button_LiderlikTablosu);
        Button btnNasilOynanir = findViewById(R.id.button_NasilOynanir);

        btnCarkiCevir.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CarkActivity.class);
            startActivity(intent);
        });

        btnLiderlikTablosu.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LiderlikTablosuActivity.class);
            startActivity(intent);
        });

        btnNasilOynanir.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NasilOynanirActivity.class);
            startActivity(intent);
        });
    }

    //  Süreyi başlatan metod
    private void startTimer() {
        long sonHakTarihi = CarkHakYonetimi.getSonHakTarihi(this);
        long suan = System.currentTimeMillis();
        long hedefZaman = sonHakTarihi + 24 * 60 * 60 * 1000;
        long kalanMs = hedefZaman - suan;

        if (kalanMs > 0) {
            new CountDownTimer(kalanMs, 1000) {
                public void onTick(long millisUntilFinished) {
                    long saat = (millisUntilFinished / (1000 * 60 * 60)) % 24;
                    long dakika = (millisUntilFinished / (1000 * 60)) % 60;
                    long saniye = (millisUntilFinished / 1000) % 60;
                    String zaman = String.format("Yeni haklar: %02d:%02d:%02d sonra", saat, dakika, saniye);
                    textKalanSure.setText(zaman);
                }

                public void onFinish() {
                    textKalanSure.setText("Haklar yenilendi!");
                }
            }.start();
        } else {
            textKalanSure.setText("Haklar yenilendi!");
        }
    }

    //  Ana menüye her dönüldüğünde kalan hakkı güncelle
    @Override
    protected void onResume() {
        super.onResume();
        int kalanHak = CarkHakYonetimi.hakGetir(this);
        textKalanHak.setText("Kalan Hak: " + kalanHak + "/3");
    }
}
