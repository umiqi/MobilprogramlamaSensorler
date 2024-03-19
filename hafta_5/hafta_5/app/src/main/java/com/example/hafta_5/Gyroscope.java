package com.example.hafta_5;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Gyroscope extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor gyroscope;
    private TextView rotationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);

        // Sensör yöneticisini başlat
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Gyroskop sensörünü al
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        // TextView'i tanımla
        rotationTextView = findViewById(R.id.rotationTextView);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            // Cihazın dönme verilerini al
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // Dönme verilerini TextView'de göster
            rotationTextView.setText("X: " + x + "\nY: " + y + "\nZ: " + z);
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
        sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Sensör dinleyicisini devre dışı bırak
        sensorManager.unregisterListener(this);
    }
}
