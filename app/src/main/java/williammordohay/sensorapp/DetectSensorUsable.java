package williammordohay.sensorapp;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class DetectSensorUsable extends AppCompatActivity {

    Sensor temperature;
    SensorManager sm;
    boolean isSensoravailable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_sensor_usable);
        //create sensor manager
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        try{
            temperature = sm.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

            //Register sensor listener
            isSensoravailable = sm.registerListener((SensorEventListener) this,temperature, SensorManager.SENSOR_DELAY_NORMAL);
        }catch(Exception ex){
            Toast.makeText(this,"sensor temperature isn't available",Toast.LENGTH_LONG).show();

        }



    }
}
