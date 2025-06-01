package com.example.kodlamacarki;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LiderlikTablosuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liderlik_tablosu);

        TextView toplamPuanText = findViewById(R.id.toplamPuanText);
        int toplamPuan = PuanYonetimi.toplamPuaniGetir(this);
        toplamPuanText.setText("Toplam Puanınız: " + toplamPuan);

        Button btnGeri = findViewById(R.id.btnGeriLiderlik);
        btnGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ana Menüye geri dön
                Intent intent = new Intent(LiderlikTablosuActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}
