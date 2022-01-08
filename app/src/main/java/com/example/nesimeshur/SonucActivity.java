package com.example.nesimeshur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SonucActivity extends AppCompatActivity {
    private TextView textSkor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc);

        int skor;
        textSkor = findViewById(R.id.textSkor);
        textSkor.setText("Skor:" + OyunActivity.skor);

    }
}