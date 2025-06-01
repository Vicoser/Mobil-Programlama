package com.example.kodlamacarki;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SoruActivity extends AppCompatActivity {

    private TextView soruMetni, sureText;
    private Button[] secenekler = new Button[4];
    private Soru aktifSoru;
    private CountDownTimer timer;
    private String gelenPuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soru);

        soruMetni = findViewById(R.id.soruMetni);
        sureText = findViewById(R.id.sureText);
        secenekler[0] = findViewById(R.id.btnSecenek1);
        secenekler[1] = findViewById(R.id.btnSecenek2);
        secenekler[2] = findViewById(R.id.btnSecenek3);
        secenekler[3] = findViewById(R.id.btnSecenek4);

        // Puanı al
        gelenPuan = getIntent().getStringExtra("puan");
        int puan = Integer.parseInt(gelenPuan);

        // Soru seç
        aktifSoru = soruGetir(puan);
        soruMetni.setText(aktifSoru.getSoruMetni());
        String[] secenekText = aktifSoru.getSecenekler();

        for (int i = 0; i < 4; i++) {
            secenekler[i].setText(secenekText[i]);
            final int index = i;
            secenekler[i].setOnClickListener(v -> cevabiKontrolEt(index));
        }

        // Süre başlat (1 dakika)
        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                sureText.setText("Kalan Süre: " + millisUntilFinished / 1000 + " sn");
            }

            @Override
            public void onFinish() {
                Toast.makeText(SoruActivity.this, "Süre doldu!", Toast.LENGTH_SHORT).show();
                CarkHakYonetimi.hakAzalt(SoruActivity.this);
                bitir();
            }
        }.start();
    }

    private Soru soruGetir(int puan) {
        List<Soru> kolay = new ArrayList<>();
        List<Soru> orta = new ArrayList<>();
        List<Soru> zor = new ArrayList<>();

        kolay.add(new Soru(
                "Java'da tam sayı tanımlamak için hangi anahtar kelime kullanılır?",
                new String[]{"int", "double", "String", "boolean"}, 0));

        kolay.add(new Soru(
                "HTML'de bir bağlantı (link) oluşturmak için hangi etiket kullanılır?",
                new String[]{"<link>", "<a>", "<href>", "<connect>"}, 1));

        kolay.add(new Soru(
                "Python'da çıktıyı ekrana yazdırmak için hangi komut kullanılır?",
                new String[]{"echo", "print", "write", "console"}, 1));

        kolay.add(new Soru(
                "C dilinde yorum satırı başlatmak için ne yazılır?",
                new String[]{"//", "/*", "--", "#"}, 0));

        kolay.add(new Soru(
                "Kodun tekrar tekrar çalışmasını sağlayan yapılara ne ad verilir?",
                new String[]{"Dizi", "Fonksiyon", "Döngü", "Koşul"}, 2));


        orta.add(new Soru(
                "Aşağıdaki döngülerden hangisi koşul sağlanmasa bile en az bir kez çalışır?",
                new String[]{"for", "while", "do-while", "if"}, 2));

        orta.add(new Soru(
                "Bir dizinin tüm elemanlarını taramak için hangisi uygundur?",
                new String[]{"break", "for", "switch", "continue"}, 1));

        orta.add(new Soru(
                "int[] dizi = new int[5]; satırında kaç elemanlık yer ayrılır?",
                new String[]{"0", "4", "5", "6"}, 2));

        orta.add(new Soru(
                "Javascript'te '===' operatörü neyi kontrol eder?",
                new String[]{"Tip kontrolü", "Değer eşitliği", "Tip ve değer", "Büyüklük"}, 2));

        orta.add(new Soru(
                "Bir değişken sadece bir sınıf örneği ile paylaşılıyorsa ne olmalıdır?",
                new String[]{"public", "static", "private", "void"}, 1));

        zor.add(new Soru(
                "Recursive (özyinelemeli) fonksiyon nedir?",
                new String[]{
                        "Kendisini çağıran fonksiyondur",
                        "Sonsuz döngüde çalışan fonksiyondur",
                        "Başka fonksiyonları çağıran fonksiyondur",
                        "Sadece bir kez çalışan fonksiyondur"}, 0));

        zor.add(new Soru(
                "Binary Search algoritması nasıl çalışır?",
                new String[]{
                        "Her elemanı sırayla kontrol eder",
                        "Yalnızca son elemanı kontrol eder",
                        "Ortadaki elemanla başlar ve aramayı ikiye böler",
                        "Verileri rastgele kontrol eder"}, 2));

        zor.add(new Soru(
                "Java'da 'NullPointerException' hatası ne zaman alınır?",
                new String[]{
                        "Dizi boyutu sıfırsa",
                        "Null olan nesneye erişmeye çalışınca",
                        "İki değişken aynıysa",
                        "Bellek taşarsa"}, 1));

        zor.add(new Soru(
                "Stack veri yapısında son giren öğe...",
                new String[]{"İlk çıkar", "Son çıkar", "Hiç çıkmaz", "Ortaya gider"}, 1));

        zor.add(new Soru(
                "Merge sort algoritması kaç parçaya ayırarak çalışır?",
                new String[]{"2", "3", "4", "5"}, 0));


        if (puan == 50) return kolay.get(new Random().nextInt(kolay.size()));
        else if (puan == 100) return orta.get(new Random().nextInt(orta.size()));
        else return zor.get(new Random().nextInt(zor.size()));
    }

    private void cevabiKontrolEt(int secilenIndex) {

        timer.cancel();
        if (secilenIndex == aktifSoru.getDogruSecenekIndex()) {
            Toast.makeText(this, "Doğru! +" + gelenPuan + " puan", Toast.LENGTH_SHORT).show();

            // Puanı kaydet
            PuanYonetimi.puanEkle(this, Integer.parseInt(gelenPuan));
        } else {
            Toast.makeText(this, "Yanlış cevap!", Toast.LENGTH_SHORT).show();
        }

        bitir();
    }

    private void bitir() {
        finish(); // Geri dön
    }
}
