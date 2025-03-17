package com.example.kodlamacarki;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Butonlar

        Button btnCarkiCevir = findViewById(R.id.button_CarkiCevir);
        Button btnLiderlikTablosu = findViewById(R.id.button_LiderlikTablosu);
        Button btnNasilOynanir =findViewById(R.id.button_NasilOynanir);

        //Çarkı çevirme butonu sayfa değişikliği

        btnCarkiCevir.setOnClickListener(view -> {
            Intent intent  = new Intent(MainActivity.this, CarkActivity.class);
            startActivity(intent);
        });
        //Liderlik tablosu butonu sayfa değişikliği

        btnLiderlikTablosu.setOnClickListener(view ->{
            Intent intent = new Intent(MainActivity.this,LiderlikTablosuActivity.class);
            startActivity(intent);
        });

        //Nasıl oynanır butonu sayfa değişikliği
        btnNasilOynanir.setOnClickListener(view->{
            Intent intent = new Intent(MainActivity.this,NasilOynanirActivity.class);
            startActivity(intent);
        });
    }
}