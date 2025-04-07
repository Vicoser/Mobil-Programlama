package com.example.seekbarsehiruygulamasi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Ekran2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ekran2);

        ListView listView = (ListView) findViewById(R.id.listView);
        Button butonGeri = (Button) findViewById(R.id.buttonGeri);

        String result = getIntent().getStringExtra("result");
        ArrayList<String> oneItemList = new ArrayList<>();
        oneItemList.add(result);



        ArrayList<String> results = getIntent().getStringArrayListExtra("results");

        if (results != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, results);
            listView.setAdapter(adapter);

            if (results.size() >= 3) {
                Toast.makeText(Ekran2.this, "🎉 Oyun bitti!", Toast.LENGTH_LONG).show();
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, oneItemList);
        listView.setAdapter(adapter);

        butonGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ekran2.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}