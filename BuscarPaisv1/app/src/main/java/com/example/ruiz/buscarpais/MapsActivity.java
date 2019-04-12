package com.example.ruiz.buscarpais;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    EditText pais;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        pais=findViewById(R.id.txtPais);
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
        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(23, 102);

        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Mexico"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

    }

    public  void buscar(View v){
        String pasi=pais.getText().toString();
        Toast.makeText(this, "Buscando", Toast.LENGTH_SHORT).show();
        Geocoder geocoder=new Geocoder(this, Locale.getDefault());
        List<Address>addresses=null;
        try {
            addresses=geocoder.getFromLocationName(pasi,1);
            if(addresses==null){
                return;
            }
                Address location=addresses.get(0);
                double lat=location.getLatitude();
                double log=location.getLongitude();
                LatLng latLng=new LatLng(lat,log);
                mMap.addMarker(new MarkerOptions().position(latLng).title("Pais Buscado"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,6));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
