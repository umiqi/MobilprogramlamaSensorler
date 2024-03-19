package com.example.hafta_5;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Manyetik extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor magneticFieldSensor;
    private TextView magneticFieldXTextView;
    private TextView magneticFieldYTextView;
    private TextView magneticFieldZTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manyetik);

        // Sensör yöneticisini başlat
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Manyetik alan sensörünü al
        magneticFieldSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        // TextView'leri tanımla
        magneticFieldXTextView = findViewById(R.id.magneticFieldXTextView);
        magneticFieldYTextView = findViewById(R.id.magneticFieldYTextView);
        magneticFieldZTextView = findViewById(R.id.magneticFieldZTextView);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            // Manyetik alan değerlerini al
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // TextView'lerde manyetik alan değerlerini göster
            magneticFieldXTextView.setText("X: " + x);
            magneticFieldYTextView.setText("Y: " + y);
            magneticFieldZTextView.setText("Z: " + z);
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
        sensorManager.registerListener(this, magneticFieldSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Sensör dinleyicisini devre dışı bırak
        sensorManager.unregisterListener(this);
    }
}
