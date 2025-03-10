package com.example.listviewsehirodev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ekran2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekran2);

        boolean dogruMu = getIntent().getBooleanExtra("dogruMu", false);
        int dogruPlaka = getIntent().getIntExtra("dogruPlaka", -1); // Doğru plaka bilgisini al

        if (dogruMu) {
            Toast.makeText(this, "DOĞRU!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "YANLIŞ! Doğru plaka: " + dogruPlaka, Toast.LENGTH_LONG).show();
        }

        Button geriButonu = findViewById(R.id.buttonGeri);
        geriButonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ekran2.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}