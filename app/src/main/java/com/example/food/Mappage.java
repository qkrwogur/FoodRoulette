package com.example.food;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class Mappage extends AppCompatActivity implements OnMapReadyCallback {
    private static final String TAG = "Mappage";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000; // 위치
    private FusedLocationSource locationSource; // 위치
    private MapView mapView;
    private NaverMap naverMap;
    private Button btnserch,btntest;
    TextView Txttest;
    float Lat = 0, Lng = 0;
    private static final String[] PERMISSIONS={Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};
    String m_strNaverKey = "jp1w3bsYCw5vg6bzD2S4";
    String m_strSearch = "한식";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testmap);

        mapView = findViewById(R.id.map_view);

        mapView.getMapAsync(this);
        btnserch = (Button)findViewById(R.id.btnsearch);
        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
        naverMap.addOnLocationChangeListener(location ->Toast.makeText(this,
                location.getLatitude() + ", " + location.getLongitude(),
                Toast.LENGTH_SHORT).show());
        Txttest=(TextView)findViewById(R.id.Txttest);
        btntest = (Button)findViewById(R.id.btntest);
        btntest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchNaver("천안");
               // Txttest.setText();

            }
        });
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
    public void searchNaver(final String searchObject) { // 검색어 = searchObject로 ;
        final String clientId = "jp1w3bsYCw5vg6bzD2S4";//애플리케이션 클라이언트 아이디값";
        final String clientSecret = "3X23L0Crpr";//애플리케이션 클라이언트 시크릿값";
        final int display = 5; // 보여지는 검색결과의 수
        Log.d(TAG, "들어왓니? ");

        // 네트워크 연결은 Thread 생성 필요
        new Thread() {

            @Override
            public void run() {
                try {
                    String text = URLEncoder.encode(searchObject, "UTF-8");
                    String apiURL = "https://openapi.naver.com/v1/search/blog.json?query=" + text + "&display=" + display + "&"; // json 결과
                    // Json 형태로 결과값을 받아옴.
                    URL url = new URL(apiURL);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("X-Naver-Client-Id", clientId);
                    con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                    con.connect();

                    int responseCode = con.getResponseCode();
                    BufferedReader br;

                    if(responseCode==200) { // 정상 호출
                        br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    } else {  // 에러 발생
                        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                    }

                    StringBuilder searchResult = new StringBuilder();
                    String inputLine;
                    while ((inputLine = br.readLine()) != null) {
                        searchResult.append(inputLine + "\n");

                    }
                    br.close();
                    con.disconnect();

                    String data = searchResult.toString();
                    String[] array;
                    array = data.split("\"");
                    String[] title = new String[display];
                    String[] link = new String[display];
                    String[] description = new String[display];
                    String[] bloggername = new String[display];
                    String[] postdate = new String[display];

                    int k = 0;
                    for (int i = 0; i < array.length; i++) {
                        if (array[i].equals("title"))
                            title[k] = array[i + 2];
                        if (array[i].equals("link"))
                            link[k] = array[i + 2];
                        if (array[i].equals("description"))
                            description[k] = array[i + 2];
                        if (array[i].equals("bloggername"))
                            bloggername[k] = array[i + 2];
                        if (array[i].equals("postdate")) {
                            postdate[k] = array[i + 2];
                            k++;
                        }
                    }

                    Log.d(TAG, "title잘나오니: " + title[0] + title[1] + title[2]);
                    // title[0], link[0], bloggername[0] 등 인덱스 값에 맞게 검색결과를 변수화하였다.

                } catch (Exception e) {
                    Log.d(TAG, "error : " + e);
                }

            }
        }.start();

    }

    class SearchData
    {
        String m_strName;
        String m_strAddress;
        String m_strX;
        String m_strY;

        public String toString()
        {
            return "name(" + m_strName + ") address(" + m_strAddress + ") x(" + m_strX + ") y(" + m_strY + ")";
        }
    }

}

