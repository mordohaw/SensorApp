package williammordohay.sensorapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Tp2Activity extends AppCompatActivity {

    protected double longitudeGPS, latitudeGPS;
    private String positionLat,positionLong;
    private TextView latTextView, longTextView, distanceEditText;
    private double latitudePoint=0.0, longitudePoint=0.0;
    private static int refreshTime = 2;

    //private static final int REQUEST_LOCATION=1;
    protected LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tp2);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        latTextView = (TextView) findViewById(R.id.longEditText);
        longTextView = (TextView) findViewById(R.id.latEditText);
        distanceEditText = (TextView) findViewById(R.id.distanceEditText);

        refreshList();
        //updatePosition();
    }



    public void getGpsPoint(View v){
        double oldLatittude, oldLongitude;
        if(latitudePoint != 0.0 && longitudePoint != 0.0){
            oldLatittude = latitudePoint;
            oldLongitude = longitudePoint;

            Location oldLocation = new Location("");
            oldLocation.setLatitude(oldLatittude);
            oldLocation.setLongitude(oldLongitude);

            latitudePoint = latitudeGPS;
            longitudePoint = longitudeGPS;
            Location newLocation = new Location("");
            newLocation.setLatitude(latitudePoint);
            newLocation.setLongitude(longitudePoint);

            float distanceInMeters = oldLocation.distanceTo(newLocation);
            distanceEditText.setText(" " + String.valueOf(distanceInMeters) + " m");


        }else{
            latitudePoint = latitudeGPS;
            longitudePoint = longitudeGPS;
        }


    }
    private void refreshList()
    {


        //rafraichirListe long-time task in background thread
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while(!isDestroyed())
                {
                    try
                    {
                        //dummy delay for "tpsRafraichissement" second
                        Thread.sleep(refreshTime*1000);

                        //update ui on UI thread
                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                updatePosition();


                            }
                        });
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                }



            }
        }).start();


    }


    public void updatePosition(){
        //if (checkLocation())
        getPosition();
        positionLat = String.valueOf(latitudeGPS);
        positionLong = String.valueOf(longitudeGPS);
        latTextView.setText(positionLat);//extras.getString("myLatitude");
        longTextView.setText(positionLong); //extras.getString("myLongitude");
        Toast.makeText(this, positionLat +" + lon : " + positionLong, Toast.LENGTH_SHORT).show();
        if(positionLat=="0.0" || positionLong =="0.0"){
            Toast.makeText(this, "Please wait few second, the position will be displayed. if not, go outside please. Thanks a lot", Toast.LENGTH_LONG).show();
        }
    }

    //LOCALISATION PART


    protected boolean checkLocation() {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                        "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }

    public void getPosition() {


        if (!checkLocation())
            return;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2 * 60 * 1000, 10, locationListenerGPS);
        //getLocation();
        //TextView positionTextView = (TextView) findViewById(R.id.myPositionText);
        //Gson myGson = new Gson();
        //String locUrl= UrlConstructor.getGeolocUrl();
        //String entryString = recupereDonnees(locUrl);
        //get the data from WebService


        //if(entryString != null)
        //{
        //  Localisation myLoc = myGson.fromJson(entryString,Localisation.class);

        //positionTextView.setText();
        //}
    }

    private final LocationListener locationListenerGPS = new LocationListener() {
        public void onLocationChanged(final Location location) {
            longitudeGPS = location.getLongitude();
            latitudeGPS = location.getLatitude();

            /*runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "GPS Provider update", Toast.LENGTH_SHORT).show();
                }
            });*/
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
}
