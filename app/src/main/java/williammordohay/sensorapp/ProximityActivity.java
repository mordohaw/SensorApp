package williammordohay.sensorapp;

import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class ProximityActivity extends AppCompatActivity implements SensorEventListener {

    private static final int SENSOR_SENSIVITY = 4 ;
    private Sensor proximity;
    private SensorManager sm;
    private ImageView myIv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);
        myIv = (ImageView) findViewById(R.id.presenceImage);
        initSensor();

    }

    public void initSensor(){
        //create sensor manager
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        //accelerometer part
        proximity = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        //Register sensor listener
        sm.registerListener((SensorEventListener) this,proximity,SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] >= -SENSOR_SENSIVITY && event.values[0] <= SENSOR_SENSIVITY) {
                Log.i("PROXIMITY", "onSensorChanged: " + event.sensor.getType() + " / PROXY=> " + Sensor.TYPE_PROXIMITY + "ACCELERE => " + Sensor.TYPE_ACCELEROMETER);
                //near
                Log.i("PROXIMITY", "****************** near ******************");
                Toast.makeText(getApplicationContext(), "****************** near ******************", Toast.LENGTH_SHORT).show();
                Drawable myIcon = getResources().getDrawable(R.drawable.detect_ok);
                this.myIv.setImageDrawable(myIcon);
            }
            else
            {
                Drawable myIcon = getResources().getDrawable( R.drawable.no_detection );
                this.myIv.setImageDrawable(myIcon);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
