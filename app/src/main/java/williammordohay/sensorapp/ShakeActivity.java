package williammordohay.sensorapp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ShakeActivity extends AppCompatActivity implements SensorEventListener {

    private boolean hasCameraFlash, flashLightActivate=false;
    private Sensor accelerometer;
    private SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
        hasCameraFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        initSensor();
    }

    public void initSensor(){
        //create sensor manager
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        //accelerometer part
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //Register sensor listener
        sm.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }


    private void flashLight(boolean flashState){
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, !flashState);
            flashLightActivate = !flashState;
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float x,y,z;
        x = event.values[0];
        y=event.values[1];
        z=event.values[2];
        float accelerationSquareRoot = (x*x+y*y+z*z)/ (sm.GRAVITY_EARTH * sm.GRAVITY_EARTH);
        if(accelerationSquareRoot>1.8){
            if(hasCameraFlash){
                flashLight(flashLightActivate);

            }else{
                Toast.makeText(ShakeActivity.this, "No flash available on your device", Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
