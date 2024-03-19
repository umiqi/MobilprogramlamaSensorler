package com.example.hafta_5;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hafta_5.R;

public class Titresim extends AppCompatActivity {

    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titresim); // activity_titresim layoutunu kullan
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE); // VIBRATOR_SERVICE sabitini Context.VIBRATOR_SERVICE olarak kullan
    }

    public void startVibration(View view) {
        if (vibrator.hasVibrator()) {
            // Cihaz titreşim destekliyorsa ve titreşim izni varsa
            vibrator.vibrate(1000); // 1000 milisaniye (1 saniye) titreşim gönder
        } else {
            // Cihaz titreşim desteklemiyorsa veya titreşim izni yoksa
            Toast.makeText(this, "Cihazınız titreşimi desteklemiyor veya izin verilmedi.", Toast.LENGTH_SHORT).show();
        }
    }
}
