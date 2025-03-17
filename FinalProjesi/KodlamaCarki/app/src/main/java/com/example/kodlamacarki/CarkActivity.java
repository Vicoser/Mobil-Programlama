package com.example.kodlamacarki;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.WheelItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarkActivity extends AppCompatActivity {

    private LuckyWheel sansliCark;
    List<WheelItem> wheelItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cark);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        Carkitemolustur();
        sansliCark = findViewById(R.id.luckyWheel);
        sansliCark.addWheelItems(wheelItems);


        Button cevir = findViewById(R.id.btn_CarkCevirEkran);
        cevir.setOnClickListener(v -> {
            Random random = new Random();
            int randomIndex = random.nextInt(wheelItems.size());
            sansliCark.rotateWheelTo(randomIndex);
        });
    }

    private void Carkitemolustur() {

        wheelItems = new ArrayList<>();

        wheelItems.add(new WheelItem(Color.RED, BitmapFactory.decodeResource(getResources(), R.drawable.ic_coin_temiz), "10 Puan"));
        wheelItems.add(new WheelItem(Color.BLUE, BitmapFactory.decodeResource(getResources(), R.drawable.ic_coin_temiz), "20 Puan"));
        wheelItems.add(new WheelItem(Color.GREEN, BitmapFactory.decodeResource(getResources(), R.drawable.ic_coin_temiz), "30 Puan"));
        wheelItems.add(new WheelItem(Color.YELLOW, BitmapFactory.decodeResource(getResources(), R.drawable.ic_coin_temiz), "40 Puan"));
        wheelItems.add(new WheelItem(Color.MAGENTA, BitmapFactory.decodeResource(getResources(), R.drawable.ic_coin_temiz), "50 Puan"));

    }

}