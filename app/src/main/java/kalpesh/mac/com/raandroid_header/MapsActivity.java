package kalpesh.mac.com.raandroid_header;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import kalpesh.mac.com.raandroid_header.model.Restaurant;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        Intent I = getIntent();
        if (I.getExtras() != null) {
            if (I.getExtras().get("pachet") != null) {
                Bundle b = (Bundle) I.getExtras().get("pachet");
                for (String key : b.keySet()) {
                    Restaurant r = (Restaurant) b.get(key);
                    LatLng rest = new LatLng(r.getLatitude(), r.getLongitude());
//                    Log.i("LOCATIOOOOOON", r.getLatitude() + "" + r.getLongitude());
//                    Log.i("Rested?", rest.toString());
                    mMap.addMarker(new MarkerOptions().position(rest).title(r.getName()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(rest));
                }
            }
            if (I.getExtras().get("single") != null) {
                Bundle b = (Bundle) I.getExtras().get("single");
                Restaurant r = (Restaurant) b.get("item");
                LatLng rest = new LatLng(r.getLatitude(), r.getLongitude());
                mMap.addMarker(new MarkerOptions().position(rest).title(r.getName()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(rest));
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mMap.setMyLocationEnabled(true);


            }
        }
        else{
        // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(-34, 151);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
        mMap.setMinZoomPreference(13);
    }
}
