package williammordohay.sensorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    private Intent nextActivityIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void goToListActivity(View v){
        nextActivityIntent = new Intent(MenuActivity.this, SensorListActivity.class);

        startActivity(nextActivityIntent);

    }

    public void goToDetectSensorActivity(View v){
        nextActivityIntent = new Intent(MenuActivity.this, DetectSensorUsable.class);

        startActivity(nextActivityIntent);

    }

    public void goToShakeActivity(View v){
        nextActivityIntent = new Intent(MenuActivity.this, ShakeActivity.class);

        startActivity(nextActivityIntent);

    }



    public void goToAccelerometerActivity(View v){
        nextActivityIntent = new Intent(MenuActivity.this, AccelerometerActivity.class);

        startActivity(nextActivityIntent);

    }

    public void goToDirectionActivity(View v){
        nextActivityIntent = new Intent(MenuActivity.this, DirectionActivity.class);

        startActivity(nextActivityIntent);

    }

    public void goToProximityActivity(View v){
        nextActivityIntent = new Intent(MenuActivity.this, ProximityActivity.class);

        startActivity(nextActivityIntent);

    }




}
