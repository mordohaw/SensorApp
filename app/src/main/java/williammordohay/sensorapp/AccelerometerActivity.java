package williammordohay.sensorapp;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener  {

    private Sensor accelerometer;
    private SensorManager sm;
    private RelativeLayout myLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        initSensor();

    }


    public void initSensor(){
        //create sensor manager
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        //accelerometer part
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //Register sensor listener
        sm.registerListener((SensorEventListener) this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x,y,z;
            x = event.values[0];
            y=event.values[1];
            z=event.values[2];

            myLayout = (RelativeLayout) findViewById(R.id.accelerometerLayout);

            if(x>5){
                myLayout.setBackgroundColor(Color.GREEN);
            }else{
                if(y>5){
                    myLayout.setBackgroundColor(Color.BLACK);
                }else{
                    if(z>5){
                        myLayout.setBackgroundColor(Color.RED);
                    }
                }
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
