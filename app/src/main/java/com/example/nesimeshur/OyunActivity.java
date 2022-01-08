package com.example.nesimeshur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OyunActivity extends AppCompatActivity {
    private TextView textSoru, textSkor, textSoruSayısı, textSure;
    private RadioButton rb1, rb2, rb3;
    private Button btnNext;
    private RadioGroup radioGroup;

    int ToplamSoruSayısı;
    int sayac = 0;
    static int skor;
    boolean cevap;
    ColorStateList dfRbColor;

    CountDownTimer countDownTimer;
    private SoruModel currentQuestion;
    private List<SoruModel> soruListesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun);

        soruListesi = new ArrayList<>();

        textSoru = findViewById(R.id.Soru);
        textSkor = findViewById(R.id.textSkor);
        textSoruSayısı = findViewById(R.id.textSoruSayısı);
        textSure = findViewById(R.id.Sure);
        rb1 = findViewById(R.id.secenek1);
        rb2 = findViewById(R.id.secenek2);
        rb3 = findViewById(R.id.secenek3);
        btnNext = findViewById(R.id.devam);
        radioGroup = findViewById(R.id.radioGroup);

        dfRbColor = rb1.getTextColors();
        addQuestions();
        ToplamSoruSayısı = soruListesi.size();
        sıradakiSoru();




        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cevap == false) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        cevapKontrol();
                        countDownTimer.cancel();
                    } else {
                        Toast.makeText(OyunActivity.this, "Lütfen birini seçiniz", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    sıradakiSoru();
                }
            }
        });
    }

    private void cevapKontrol() {
        cevap = true;
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int soruSayisi = radioGroup.indexOfChild(rbSelected) + 1;
        if (soruSayisi == currentQuestion.getCorrectAnsNo()) {
            skor++;
            textSkor.setText("Skor:" + skor);
        }
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);

        switch (currentQuestion.getCorrectAnsNo()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                break;
        }
        if (sayac < ToplamSoruSayısı) {
            btnNext.setText("Devam");

        } else {
            btnNext.setText("Son");
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(OyunActivity.this, SonucActivity.class);
                    startActivity(intent);
                }
            });

        }
    }

    private void sıradakiSoru() {
        radioGroup.clearCheck();
        rb1.setTextColor(dfRbColor);
        rb2.setTextColor(dfRbColor);
        rb3.setTextColor(dfRbColor);

        if (sayac < ToplamSoruSayısı) {
            timer();
            currentQuestion = soruListesi.get(sayac);
            textSoru.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            sayac++;
            btnNext.setText("İşaretle");
            textSoruSayısı.setText("Soru:" + sayac + "/" + ToplamSoruSayısı);
            cevap = false;


        } else {
            finish();
        }

    }


    private void timer() {
        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long countDownInterval) {
                textSure.setText("00:" + countDownInterval / 1000);
            }

            @Override
            public void onFinish() {
                sıradakiSoru();
            }
        }.start();
    }


    private void addQuestions() {
        soruListesi.add(new SoruModel("Gazintep ilinin nesi meşhurdur?", "Baklava", "Kadayıf", "Künefe", 1));
        soruListesi.add(new SoruModel("Adıyaman ilinin nesi meşhurdur?", "Peri Bacaları", "Nemrut Dağı", "Düden Şelalesi", 2));
        soruListesi.add(new SoruModel("Adana ilinin hangi yemeği meşhurdur?", "Lahmacun", "İskender", "Kebap", 3));
        soruListesi.add(new SoruModel("Çorum ilinin nesi meşhurdur?", "Fındık", "Badem", "Leblebi", 3));
        soruListesi.add(new SoruModel("Antalya ilinin nesi meşhurdur?", "Peri Bacaları", "Abant Gölü", "Düden Şelalesi", 3));
        soruListesi.add(new SoruModel("Şanlıurfa ilinin nesi meşhurdur?", "Çiğköfte", "Mantı", "Börek", 1));
        soruListesi.add(new SoruModel("Aydın ilinin nesi meşhurdur?", "Karpuz", "İncir", "Kiraz", 2));
        soruListesi.add(new SoruModel("Burdur ilinin nesi meşhurdur?", "İnsuyu Mağarası", "Pamukkale Travertenleri", "Taş Mescit", 1));
        soruListesi.add(new SoruModel("Isparta ilinin nesi meşhurdur?", "Papatya", "Gül", "Lavanta", 2));
        soruListesi.add(new SoruModel("Trabzon ilinin nesi meşhurdur?", "Hamsi", "İncir", "Kiraz", 1));
    }
}