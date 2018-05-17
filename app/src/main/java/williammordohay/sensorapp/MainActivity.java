package williammordohay.sensorapp;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView xTextView, yTextView, zTextView, giroXTextView, giroYTextView, giroZTextView, resoTextView, minDelayTextView, versionTextView;
    Sensor accelerometer, gyroscope;
    SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create sensor manager
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        //accelerometer part
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        gyroscope = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        //Register sensor listener
        sm.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this,gyroscope,SensorManager.SENSOR_DELAY_NORMAL);

        //Asign TextViews
        xTextView = (TextView) findViewById(R.id.xTextView);
        yTextView = (TextView) findViewById(R.id.yTextView);
        zTextView = (TextView) findViewById(R.id.zTextView);
        giroXTextView = (TextView) findViewById(R.id.xGiro);
        giroYTextView = (TextView) findViewById(R.id.yGiro);
        giroZTextView = (TextView) findViewById(R.id.zGiro);
        resoTextView = (TextView) findViewById(R.id.resoTextView);
        minDelayTextView = (TextView) findViewById(R.id.minDelayTextView);
        versionTextView = (TextView) findViewById(R.id.versionTextView);

        resoTextView.setText("Resolution : "+(int) accelerometer.getResolution());
        versionTextView.setText("Version : " + accelerometer.getVersion());
        minDelayTextView.setText("Min Delay : "+ accelerometer.getMinDelay());

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            xTextView.setText("X: " + event.values[0]);
            yTextView.setText("Y: " + event.values[1]);
            zTextView.setText("Z: " + event.values[2]);
        }else if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE){

            giroXTextView.setText("X: " + event.values[0]);
            giroYTextView.setText("Y: " + event.values[1]);
            giroZTextView.setText("Z: " + event.values[2]);
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
