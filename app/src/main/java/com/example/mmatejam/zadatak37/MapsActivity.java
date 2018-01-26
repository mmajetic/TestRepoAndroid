package com.example.mmatejam.zadatak37;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final LatLng AMFITEATAR =
            new LatLng(44.873222, 13.850155);
    private static final LatLng BUJE =
            new LatLng(45.413944,13.665951);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_sethybrid:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.menu_showtraffic:
                mMap.setTrafficEnabled(true);
                break;
            case R.id.menu_zoomin:
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
                break;

            case R.id.menu_zoomout:
                mMap.animateCamera(CameraUpdateFactory.zoomOut());
                break;
            case R.id.menu_gotolocation:
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(AMFITEATAR) // Sets the center of the map to
                        // Golden Gate Bridge
                        .zoom(17)                   // Sets the zoom
                        .bearing(90) // Sets the orientation of the camera to east
                        .tilt(30)    // Sets the tilt of the camera to 30 degrees
                        .build();    // Creates a CameraPosition from the builder
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(
                        cameraPosition));
                break;
            case R.id.menu_addmarker:


                mMap.addMarker(new MarkerOptions()
                        .position(AMFITEATAR)
                        .title("Arena")
                        .icon(BitmapDescriptorFactory
                                // .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                                .fromResource(R.drawable.pushpin)));
                break;
            case R.id.menu_getcurrentlocation:
                // ---get your current location and display a blue dot---
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                }
                mMap.setMyLocationEnabled(true);

                break;

            case R.id.menu_showcurrentlocation:
                Location myLocation = mMap.getMyLocation();
                LatLng myLatLng = new LatLng(myLocation.getLatitude(),
                        myLocation.getLongitude());

                CameraPosition myPosition = new CameraPosition.Builder()
                        .target(myLatLng).zoom(17).bearing(90).tilt(30).build();
                mMap.animateCamera(
                        CameraUpdateFactory.newCameraPosition(myPosition));
                //da ucita kartu na kraju
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                break;
            case R.id.menu_lineconnecttwopoints:
                //---add a marker at Apple---
                mMap.addMarker(new MarkerOptions()
                        .position(BUJE)
                        .title("Buje")

                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_AZURE)));

                //---draw a line connecting Apple and Golden Gate Bridge---
                mMap.addPolyline(new PolylineOptions()
                        .add(AMFITEATAR, BUJE).width(5).color(Color.RED));
                break;
            case R.id.menu_zaBodove:
                zaBodove();
                break;
        }
        return true;
    }

    public void zaBodove(){
        LatLng ZAGREB = new LatLng(45.815011,15.981919);
        LatLng SPLIT = new LatLng(43.508132,16.440193);
        LatLng RIJEKA = new LatLng(45.327063,14.442176);
        LatLng OSIJEK = new LatLng(45.554962,18.695514);
        float max = 0.0f;
        String grad1 = new String();
        String grad2 = new String();
        List<LatLng> lista = new ArrayList<LatLng>();
        lista.add(ZAGREB); lista.add(OSIJEK); lista.add(SPLIT); lista.add(RIJEKA);
        List<String> nazivi = new ArrayList<String>();
        nazivi.add("Zagreb"); nazivi.add("Osijek"); nazivi.add("Split"); nazivi.add("Rijeka");
        for (int i =0; i<lista.size();++i){
            mMap.addMarker(new MarkerOptions()
                    .position(lista.get(i))
                    .title(nazivi.get(i)).icon(BitmapDescriptorFactory.defaultMarker()));
            int j = i + 1;
            for (;j<lista.size();++j){
                mMap.addPolyline(new PolylineOptions()
                        .add(lista.get(i), lista.get(j)).width(5).color(Color.RED));

                Location loc1 = new Location("");
                loc1.setLatitude(lista.get(i).latitude);
                loc1.setLongitude(lista.get(i).longitude);
                Location loc2 = new Location("");
                loc2.setLatitude(lista.get(j).latitude);
                loc2.setLongitude(lista.get(j).longitude);
                float res = loc1.distanceTo(loc2);
                if (res > max){
                    max = res;
                    grad1=nazivi.get(i); grad2=nazivi.get(j);
                }
            }
        }
        Context context = getApplicationContext();
        CharSequence text = "Najveca udaljenost je izmedju " + grad1 + " i " + grad2 + " i iznosi " + max/1000 + "km!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
