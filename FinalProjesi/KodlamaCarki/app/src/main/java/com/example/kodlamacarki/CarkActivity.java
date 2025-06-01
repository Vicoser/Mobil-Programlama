package com.example.kodlamacarki;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CarkActivity extends AppCompatActivity {

    private CustomWheelView wheelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cark);
        wheelView = findViewById(R.id.wheelView); // CustomWheelView id'si

        wheelView.setOnSpinCompleteListener(new CustomWheelView.OnSpinCompleteListener() {
            @Override
            public void onSpinComplete(String kazandiginPuan) {
                // Hak azalt
                CarkHakYonetimi.hakAzalt(CarkActivity.this);

                // Soru ekranına geç
                Intent intent = new Intent(CarkActivity.this, SoruActivity.class);
                intent.putExtra("puan", kazandiginPuan);
                startActivity(intent);
                finish();
            }
        });


        // ⛔ Hak kontrolü
        int kalanHak = CarkHakYonetimi.hakGetir(this);
        if (kalanHak <= 0) {
            Toast.makeText(this, "Bugünlük çevirme hakkın kalmadı!", Toast.LENGTH_LONG).show();
            finish(); // Geri dön
            return;
        }


        // Ana menüye dönüş butonu
        Button btnAnaMenu = findViewById(R.id.btnAnaMenuDon);
        btnAnaMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarkActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    //  Çark döndükten sonra hak azaltmak için çağırılacak fonksiyon
    public void hakAzaltVeDevamEt() {
        CarkHakYonetimi.hakAzalt(this);
    }
}
