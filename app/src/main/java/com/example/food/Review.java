package com.example.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.geometry.Tm128;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.util.FusedLocationSource;

public class Review extends AppCompatActivity implements OnMapReadyCallback {
    String[] test={"1","2","3","4","5","6","7","8","9","10"};
    private LinearLayout containertalbe;
    Button rv_write;    // 리뷰 작성 버튼
    TextView rv_title;  // 가게 이름 텍스트뷰
    private NaverMap mNaverMap;
    private FusedLocationSource mLocationSource;
    private static final int PERMISSION_REQUEST_CODE = 100;
    String get_title;
    LatLng latLng;
    CameraPosition cameraPosition;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);
        rv_write=(Button)findViewById(R.id.rv_write);
        rv_title=(TextView)findViewById(R.id.rv_title); // 가게이름
        containertalbe=(LinearLayout)findViewById(R.id.rv_Reviewtable);
        Intent info_intent=getIntent();
        get_title=info_intent.getStringExtra("title");// get_title = 가게이름
        double mx=info_intent.getDoubleExtra("mx",37.5666102);
        double my=info_intent.getDoubleExtra("my",126.9783881);
        Tm128 tm128 = new Tm128(mx, my);
        latLng = tm128.toLatLng();
        rv_title.setText(get_title);

        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.rv_navermap);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.rv_navermap, mapFragment).commit();
        }


        mapFragment.getMapAsync(this);

        for (int i=0;i< test.length;i++){
            Reviewlist n_layout= new Reviewlist(getApplicationContext());
            TextView rv_Txt = (TextView)n_layout.findViewById(R.id.rv_Txt);
            rv_Txt.setText(test[i]);
            containertalbe.addView(n_layout);
        }
        rv_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ReviewWrite.class);
                intent.putExtra("title",rv_title.getText().toString());
                startActivity(intent);
            }
        });
    }
    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        mNaverMap = naverMap;
        InfoWindow infoWindow = new InfoWindow();
        infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(getApplicationContext()) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return get_title;
            }
        });
        infoWindow.setPosition(latLng);
        infoWindow.open(mNaverMap);
        cameraPosition =  new CameraPosition(latLng, 16);
        mNaverMap.setCameraPosition(cameraPosition);


    }
}
