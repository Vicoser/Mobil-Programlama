package com.example.seekbarsehiruygulamasi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    long startTime = 0;
    String[] sehirler = {
            "Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin",
            "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale",
            "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum",
            "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Isparta", "Mersin",
            "istanbul", "İzmir", "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir", "Kocaeli",
            "Konya", "Kütahya", "Malatya", "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir",
            "Niğde", "Ordu", "Rize", "Sakarya", "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat",
            "Trabzon", "Tunceli", "Şanlıurfa", "Uşak", "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt",
            "Karaman", "Kırıkkale", "Batman", "Şırnak", "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük",
            "Kilis", "Osmaniye", "Düzce"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button butonBasla = (Button) findViewById(R.id.buttonBasla);
        Button butonOnayla = (Button) findViewById(R.id.buttonOnayla);
        EditText editTextSehir = (EditText) findViewById(R.id.editTextSehir);
        SeekBar seekBarSehir = (SeekBar) findViewById(R.id.seekBarSehir);
        TextView textViewSehir = (TextView) findViewById(R.id.textViewSehir);



        seekBarSehir.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                seekBarSehir.setMax(80);
                textViewSehir.setText(String.valueOf(i+1));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        butonBasla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime =System.currentTimeMillis();
            }
        });

        butonOnayla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedIndex = seekBarSehir.getProgress();
                String correctCity = sehirler[selectedIndex].toLowerCase(Locale.getDefault());
                String guess = editTextSehir.getText().toString().trim().toLowerCase(Locale.getDefault());

                if (startTime == 0) {
                    Toast.makeText(MainActivity.this, "Önce 'Başla' butonuna basmalısın!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (guess.equals(correctCity)) {
                    long elapsed = (System.currentTimeMillis() - startTime) / 1000;
                    startTime = 0;

                    Intent intent = new Intent(MainActivity.this, Ekran2.class);
                    intent.putExtra("result", elapsed + " saniye - " + sehirler[selectedIndex]);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Hatalı! Tekrar deneyin.", Toast.LENGTH_SHORT).show();
                }
            }

        });



    }
}