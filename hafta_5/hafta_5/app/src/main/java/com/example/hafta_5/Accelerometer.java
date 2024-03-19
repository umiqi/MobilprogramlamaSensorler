package com.example.hafta_5;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Accelerometer extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private TextView accelerationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometre);

        // Sensör yöneticisini başlat
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Ivmeölçer sensörünü al
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // TextView'i tanımla
        accelerationTextView = findViewById(R.id.accelerationTextView);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // Cihazın ivme verilerini al
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // Telefonun yatay eğimi hesaplanır
            double horizontalAngle = Math.atan2(y, Math.sqrt(x * x + z * z)) * (180 / Math.PI);

            // Ivme verilerini TextView'de göster
            accelerationTextView.setText("X: " + x + "\nY: " + y + "\nZ: " + z +
                    "\nHorizontal Angle: " + horizontalAngle);
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
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Sensör dinleyicisini devre dışı bırak
        sensorManager.unregisterListener(this);
    }
}
