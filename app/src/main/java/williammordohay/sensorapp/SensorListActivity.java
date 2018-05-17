package williammordohay.sensorapp;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class SensorListActivity extends AppCompatActivity {

    private SensorManager sm;
    private List<Sensor> sensorList;
    private String sensorString="";
    private Button nextButton;
    private Intent nextActivityIntent;

    //UI Elements
    private TextView sensorTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_list);
        sensorTextview = findViewById(R.id.sensorListView);

        //create sensor manager
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorList = sm.getSensorList(Sensor.TYPE_ALL);
        for(int i=0; i<sensorList.size();i++){
            sensorString += sensorList.get(i).getName() + " // ";

        }
        sensorTextview.setText(sensorString);

    }

}
