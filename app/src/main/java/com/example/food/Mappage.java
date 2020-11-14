package com.example.food;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;


public class Mappage extends AppCompatActivity implements OnMapReadyCallback {
    private static final String TAG = "Mappage";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000; // 위치
    private FusedLocationSource locationSource; // 위치
    private MapView mapView;
    private NaverMap naverMap;
    private Button btnserch;
    float Lat = 0, Lng = 0;
    private static final String[] PERMISSIONS={Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naver_map);

        mapView = findViewById(R.id.map_view);

        mapView.getMapAsync(this);
        btnserch = (Button)findViewById(R.id.btnsearch);
        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
        naverMap.addOnLocationChangeListener(location ->Toast.makeText(this,
                location.getLatitude() + ", " + location.getLongitude(),
                Toast.LENGTH_SHORT).show());
        btnserch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    // 위치
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,  @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED) { // 권한 거부됨
                naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Log.d(TAG,"onMapReady");
        Marker marker = new Marker();
        marker.setPosition(new LatLng(37.5670135, 126.9783740));
        marker.setMap(naverMap);

        this.naverMap = naverMap;
        naverMap.setLocationSource(locationSource);
        ActivityCompat.requestPermissions(this,PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE);


    }
}
