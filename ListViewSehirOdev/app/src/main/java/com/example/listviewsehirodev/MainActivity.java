package com.example.listviewsehirodev;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random rnd = new Random();
    private ListView list1, list2;
    private Button buton1;

    private String[] turkiyeSehirleri = {
            "Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin",
            "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa",
            "Çanakkale", "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elazığ", "Erzincan",
            "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkâri", "Hatay",
            "Isparta", "Mersin", "İstanbul", "İzmir", "Kars", "Kastamonu", "Kayseri", "Kırklareli",
            "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Kahramanmaraş",
            "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya", "Samsun",
            "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Şanlıurfa",
            "Uşak", "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale",
            "Batman", "Şırnak", "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis",
            "Osmaniye", "Düzce"
    };

    private List<Integer> plakalar = new ArrayList<>();
    private List<String> sehirler = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list1 = findViewById(R.id.listView1);
        list2 = findViewById(R.id.listView2);
        buton1 = findViewById(R.id.button1);

        buton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rastgeleListeOlustur();
            }
        });

        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int plakaDegeri = plakalar.get(position);
                String secilenSehir = sehirler.get(position);
                int dogruPlaka = -1;

                for (int i = 0; i < turkiyeSehirleri.length; i++) {
                    if (turkiyeSehirleri[i].equals(secilenSehir)) {
                        dogruPlaka = i + 1;
                        break;
                    }
                }

                boolean dogruMu = (plakaDegeri == dogruPlaka);

                Intent intent = new Intent(MainActivity.this, ekran2.class);
                intent.putExtra("dogruMu", dogruMu);
                intent.putExtra("dogruPlaka", dogruPlaka); // Doğru plakayı ikinci sayfaya gönderiyoruz
                startActivity(intent);
            }
        });


        rastgeleListeOlustur();
    }

    private void rastgeleListeOlustur() {
        plakalar.clear();
        sehirler.clear();

        List<Integer> plakalarSirali = new ArrayList<>();
        for (int i = 1; i <= 81; i++) {
            plakalarSirali.add(i);
        }
        Collections.shuffle(plakalarSirali);

        List<String> sehirlerSirali = new ArrayList<>();
        Collections.addAll(sehirlerSirali, turkiyeSehirleri);
        Collections.shuffle(sehirlerSirali);

        for (int i = 0; i < 10; i++) {
            plakalar.add(plakalarSirali.get(i));
            sehirler.add(sehirlerSirali.get(i));
        }

        ArrayAdapter<Integer> plakaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, plakalar);
        ArrayAdapter<String> sehirAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sehirler);

        list1.setAdapter(plakaAdapter);
        list2.setAdapter(sehirAdapter);
    }
}