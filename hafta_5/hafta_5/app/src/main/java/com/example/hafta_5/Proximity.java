package com.example.hafta_5;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Proximity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private TextView verticalTextView, horizontalTextView, heightTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);

        // Sensör yöneticisini başlat
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Yakınlık sensörünü al
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // TextView'leri tanımla
        verticalTextView = findViewById(R.id.verticalTextView);
        horizontalTextView = findViewById(R.id.horizontalTextView);
        heightTextView = findViewById(R.id.heightTextView);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // Dikey, yatay ve yükseklik değerlerini al
            float vertical = event.values[0];
            float horizontal = event.values[1];
            float height = event.values[2];

            // TextView'lerde değerleri göster
            verticalTextView.setText("Dikey: " + vertical);
            horizontalTextView.setText("Yatay: " + horizontal);
            heightTextView.setText("Yükseklik: " + height);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Sensör hassasiyeti değiştiğinde burası çağrılır, genellikle burada herhangi bir şey yapmamıza gerek yok
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Sensör dinleyicisini etkinleştir
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Sensör dinleyicisini devre dışı bırak
        sensorManager.unregisterListener(this);
    }
}
