package com.example.hafta_5;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Dokunmatik extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokunmatik);

        textView = findViewById(R.id.textView);

        // TextView üzerinde dokunma olaylarını dinle
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Dokunma olayı algılandığında burası çağrılır
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Kullanıcı ekrana dokunduğunda (basılı tuttuğunda) Toast mesajı göster
                        showToast("Ekran dokunma algılandı");
                        break;
                    case MotionEvent.ACTION_UP:
                        // Kullanıcı ekrandan elini kaldırdığında Toast mesajı göster
                        showToast("Ekran dokunma bitti");
                        break;
                }
                // Olayın işlendiği belirtilmeli, böylece diğer dinleyiciler çağrılmaz
                return true;
            }
        });
    }

    // Toast mesajı gösteren yardımcı metod
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
