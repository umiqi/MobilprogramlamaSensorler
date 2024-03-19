package com.example.hafta_5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Sicaklik extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor temperatureSensor;
    private TextView temperatureTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sicaklik);

        // Sensör yöneticisini başlat
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Sıcaklık sensörünü al
        temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        // TextView'i tanımla
        temperatureTextView = findViewById(R.id.temperatureTextView);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            // Ortam sıcaklığını al
            float temperatureValue = event.values[0];
            // TextView'de sıcaklık değerini göster
            temperatureTextView.setText("Ortam Sıcaklığı: " + temperatureValue + " °C");
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
        sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Sensör dinleyicisini devre dışı bırak
        sensorManager.unregisterListener(this);
    }
}
