package com.omaralashi.omaralashi.shakethedice;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager sensorManager;


    private long lastUpdate;
    private ImageView dice1;
    private ImageView dice2;
    private TextView dice1txt;
    private TextView dice2txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dice1 = (ImageView) findViewById(R.id.dice1);
        dice2 = (ImageView) findViewById(R.id.dice2);

        dice1txt= (TextView) findViewById(R.id.dice1txt);
        dice2txt= (TextView) findViewById(R.id.dice2txt);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();
    }



    //@Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }
    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
        long actualTime = event.timestamp;

        int  [] dice11 = {1,2,3,4,5,6};
        int  [] dice22 = {1,2,3,4,5,6};

        int randomStr = dice11[new Random().nextInt(dice11.length)];
        int randomStr2 = dice22[new Random().nextInt(dice22.length)];

        if (accelationSquareRoot >= 2) //
        {
            if (actualTime - lastUpdate < 1000) {
                return;
            }
            lastUpdate = actualTime;

            switch (randomStr){

                case 1:
                    dice1.setImageResource(R.drawable.dice_1);
                    break;
                case 2:
                    dice1.setImageResource(R.drawable.dice_2);
                    break;
                case 3:
                    dice1.setImageResource(R.drawable.dice_3);
                    break;
                case 4:
                    dice1.setImageResource(R.drawable.dice_4);
                    break;
                case 5:
                    dice1.setImageResource(R.drawable.dice_5);
                    break;
                case 6:
                    dice1.setImageResource(R.drawable.dice_6);
                    break;

            }

            switch (randomStr2){

                case 1:
                    dice2.setImageResource(R.drawable.dice_1);
                    break;
                case 2:
                    dice2.setImageResource(R.drawable.dice_2);
                    break;
                case 3:
                    dice2.setImageResource(R.drawable.dice_3);
                    break;
                case 4:
                    dice2.setImageResource(R.drawable.dice_4);
                    break;
                case 5:
                    dice2.setImageResource(R.drawable.dice_5);
                    break;
                case 6:
                    dice2.setImageResource(R.drawable.dice_6);
                    break;

            }
            dice1txt.setText(""+randomStr);
            dice2txt.setText(""+randomStr2);



        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        sensorManager.registerListener((SensorEventListener) this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}

