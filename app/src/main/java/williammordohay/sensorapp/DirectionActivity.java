package williammordohay.sensorapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DirectionActivity extends AppCompatActivity implements SensorEventListener {

    private Sensor accelerometer, gyroscope;
    private SensorManager sm;
    private TextView directionTextview;
    private String placement="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);
        directionTextview = findViewById(R.id.directionText);

        //create sensor manager
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        //accelerometer part
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        gyroscope = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        //Register sensor listener
        sm.registerListener((SensorEventListener) this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener((SensorEventListener) this,gyroscope,SensorManager.SENSOR_DELAY_NORMAL);

    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            /*xTextView.setText("X: " + event.values[0]);
            yTextView.setText("Y: " + event.values[1]);
            zTextView.setText("Z: " + event.values[2]);*/
            float x,y,z;
            x = event.values[0];
            y=event.values[1];
            z=event.values[2];

            if(x>5){
                //COTE
                placement = "COTE";
            }else{
                if(y>5){
                    //DEBOUT
                    placement = "DEBOUT";
                }else{
                    if(z>5){
                        //A PLAT
                        placement = "PLAT";
                    }
                }
            }
        }else if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE){
            float a,b,c;
            boolean droite=false, gauche=false, haut=false, bas=false;
            a = event.values[0];
            b=event.values[1];
            c=event.values[2];

            if(placement =="PLAT"){
                droite = c>a && c>b;
                gauche = c<a && c<b;
                bas = b>a && b>c;
                haut = b<a && b<c;

            }

            if(placement =="DEBOUT"){
                droite = c>a && c>b;
                gauche = c<a && c<b;
                haut = a>b && a>c;
                bas = a<b && a<c;
            }

            if(placement =="COTE"){
                droite = a>c && a>b;
                gauche = a<c && a<b;
                haut = c>b && c>b;
                bas = c<b && c<a;
            }

            if(droite){
                directionTextview.setText("DROITE");

            }
            if(gauche){
                directionTextview.setText("GAUCHE");
            }
            if(haut){
                directionTextview.setText("HAUT");
            }

            if(bas){
                directionTextview.setText("BAS");
            }

            /*giroXTextView.setText("X: " + event.values[0]);
            giroYTextView.setText("Y: " + event.values[1]);
            giroZTextView.setText("Z: " + event.values[2]);*/
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
