package com.example.food;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.location.LocationListener;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.geometry.Tm128;
import com.naver.maps.geometry.Utmk;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
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
import java.util.Vector;
import com.example.food.gps;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class test extends AppCompatActivity implements OnMapReadyCallback {
    private static final String TAG = "test";
    StringBuffer road = new StringBuffer();// 도로명 주소가 저장되는 변수
    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    private FusedLocationSource mLocationSource;
    private NaverMap mNaverMap;
    Button btntest, btnReverse, btnlist;
    String clientId = "jp1w3bsYCw5vg6bzD2S4";
    String clientSecret = "3X23L0Crpr";
    String m_strSearch = "한식";
    String category="";
    final int display = 5;
    String[] title = new String[display];
    String[] link = new String[display];
    String[] description = new String[display];
    String[] bloggername = new String[display];
    String[] postdate = new String[display];
    String[] mapx = new String[display];
    String[] mapy = new String[display];
    boolean flag = false;
    double latitude = 37.5670135;
    double longitude = 127.066242;
    int Index=0;
    View dialogView;
    //Marker marker = new Marker();

    // onCreate-----------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testmap);
        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // 지도 객체 생성
        FragmentManager fm = getSupportFragmentManager();

        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.navermap);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.navermap, mapFragment).commit();
        }

        // getMapAsync를 호출하여 비동기로 onMapReady 콜백 메서드 호출
        // onMapReady에서 NaverMap 객체를 받음


        // 위치를 반환하는 구현체인 FusedLocationSource 생성
        mLocationSource =
                new FusedLocationSource(this, PERMISSION_REQUEST_CODE);

        Intent intent = getIntent();
        latitude = intent.getDoubleExtra("latitude", 37.5670135);
        longitude = intent.getDoubleExtra("longitude", 127.066242);
        category = intent.getStringExtra("category");

        TextView location = findViewById(R.id.Txttest);
        location.setText("위도=" + latitude + ", 경도=" + longitude);


        btntest = (Button) findViewById(R.id.btntest);
        // 검색 버튼
        btntest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentread = new Intent(getApplicationContext(), gps.class);
                intentread.putExtra("category",category);
                startActivity(intentread);

            }

        });
        // 반환 버튼
        btnReverse = (Button) findViewById(R.id.btnReverse);
        btnReverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                road = new StringBuffer();
                Reversegeododing(latitude, longitude);
                searchNaver("문암로"+category);
                //road.toString()
            }
        });
        btnlist=(Button)findViewById(R.id.btnlist);
        btnlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentList= new Intent(getApplicationContext(), Listpage.class);
                intentList.putExtra("title",title);
                startActivity(intentList);
            }
        });
        mapFragment.getMapAsync(this);

    }

    /*private void setMarker(Marker marker, double lat, double lng) {
        //마커 위치
        marker.setPosition(new LatLng(lat, lng));
        //마커 우선순위
        //marker.setZIndex(zIndex);
        //마커 표시
        marker.setMap(mNaverMap);
    }*/

    //----------------------------------------------------------------------------------------------------------------------------
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Log.d(TAG, "onMapReady");

        // NaverMap 객체 받아서 NaverMap 객체에 위치 소스 지정
        mNaverMap = naverMap;
        mNaverMap.setLocationSource(mLocationSource);

        // 권한확인. 결과는 onRequestPermissionsResult 콜백 매서드 호출
        ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST_CODE);
    }

    // 마커 정보 저장시킬 변수들 선언
    private Vector<LatLng> markersPosition;
    private Vector<Marker> activeMarkers;

    // 지도상에 표시되고있는 마커들 지도에서 삭제
    private void freeActiveMarkers() {
        if (activeMarkers == null) {
            activeMarkers = new Vector<Marker>();
            return;
        }
        for (Marker activeMarker : activeMarkers) {
            activeMarker.setMap(null);
        }
        activeMarkers = new Vector<Marker>();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // request code와 권한획득 여부 확인
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mNaverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
            }
        }
    }

    // 지역 검색----------------------------------------------------------------------------------------------------------------------------
    public void searchNaver(final String searchObject) { // 검색어 = searchObject로 ;
        final String clientId = "jp1w3bsYCw5vg6bzD2S4";//애플리케이션 클라이언트 아이디값";
        final String clientSecret = "3X23L0Crpr";//애플리케이션 클라이언트 시크릿값";
        final int display = 5; // 보여지는 검색결과의 수

        // 네트워크 연결은 Thread 생성 필요


        new Thread() {

            @Override
            public void run() {
                try {
                    String text = URLEncoder.encode(searchObject, "UTF-8");
                    String apiURL = "https://openapi.naver.com/v1/search/local.json?query=" + text + "&display=" + display + "&sort=random"; // json 결과
                    // Json 형태로 결과값을 받아옴.
                    URL url = new URL(apiURL);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("X-Naver-Client-Id", clientId);
                    con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                    con.connect();

                    int responseCode = con.getResponseCode();
                    BufferedReader br;  // 버퍼를 활용한 입력

                    if (responseCode == 200) { // 정상 호출
                        br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    } else {  // 에러 발생
                        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                    }

                    StringBuilder searchResult = new StringBuilder();
                    // String을 연산하면 메모리 할당과 해제를 발생시키며 성능적으로 좋지 않음
                    // 그래서 나온게 StringBuilder 문자열을 더할 때 새로운 객체를 생성하는 것이 아니라 기존 데이터에 더하는 방식
                    String inputLine;
                    //readLine()은 개행문자가 포함되어야 내부 blocking이 풀리면서 wihle문이 실행한다는 것이다.
                    //스트림에서의 개행문자는 "\r\n"이 개행문자다.
                    while ((inputLine = br.readLine()) != null) {
                        searchResult.append(inputLine + "\n");

                    }
                    br.close();
                    con.disconnect();

                    String data = searchResult.toString();
                    String[] array;
                    array = data.split("\"");
                    title = new String[display];
                    link = new String[display];
                    description = new String[display];
                    bloggername = new String[display];
                    postdate = new String[display];
                    mapx = new String[display];
                    mapy = new String[display];

                    int k = 0;
                    for (int i = 0; i < array.length; i++) {
                        if (array[i].equals("title"))
                            title[k] = array[i + 2].replaceAll("<[^>]*>", " ");
                        if (array[i].equals("link"))
                            link[k] = array[i + 2].replaceAll("<[^>]*>", " ");
                        if (array[i].equals("description"))
                            description[k] = array[i + 2].replaceAll("<[^>]*>", " ");
                        if (array[i].equals("address"))
                            bloggername[k] = array[i + 2].replaceAll("<[^>]*>", " ");
                        if (array[i].equals("roadAddress")) {
                            postdate[k] = array[i + 2].replaceAll("<[^>]*>", " ");
                        }
                        if (array[i].equals("mapx")) {
                            mapx[k] = array[i + 2].replaceAll("<[^>]*>", " ");
                        }
                        if (array[i].equals("mapy")) {
                            mapy[k] = array[i + 2].replaceAll("<[^>]*>", " ");
                            k++;
                        }
                    }
                    for(int i = 0; i < display; i++){
                        if(title[i]==null)
                            title[i] = " ";
                        if(link[i]==null)
                            link[i] = " ";
                        if(description[i]==null)
                            description[i] = " ";
                        if(postdate[i]==null)
                            postdate[i] = " ";
                        if(bloggername[i]==null)
                            bloggername[i] = " ";
                    }

                    for (int i = 0; i < display; i++) {
                        Log.d(TAG, "title: " + title[i]);
                        // title[0], link[0], bloggername[0] 등 인덱스 값에 맞게 검색결과를 변수화하였다.
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(100);
                                freeActiveMarkers();
                                markersPosition = new Vector<LatLng>();
                                for (int i = 0; i < display; i++) {
                                    if (mapx[i] != null && mapx[i].length() != 0 && mapy[i] != null && mapy[i].length() != 0) {
                                        double mx = Double.parseDouble(mapx[i]);
                                        double my = Double.parseDouble(mapy[i]);
                                        Tm128 tm128 = new Tm128(mx, my);
                                        LatLng latLng = tm128.toLatLng();
                                        //setMarker(marker, latLng.latitude, latLng.longitude);
                                        markersPosition.add(new LatLng(
                                                latLng.latitude,
                                                latLng.longitude
                                        ));
                                        Log.d("maker", " maker :" + Integer.toString(i) + " latLng :" + latLng);
                                    }
                                }
                                Index=0;
                                Overlay.OnClickListener listener = overlay -> {
                                    InfoWindow infoWindow = (InfoWindow)overlay;
                                    dialogView=(View)View.inflate(test.this,R.layout.detail,null);
                                    AlertDialog.Builder dlg=new AlertDialog.Builder(test.this);
                                    Log.d("index", infoWindow.getTag().toString());
                                    int number=Integer.parseInt(infoWindow.getTag().toString());
                                    dlg.setTitle(title[number]);
                                    dlg.setView(dialogView);
                                    dlg.setPositiveButton("확인",null);
                                    dlg.setNegativeButton("취소", null);
                                    dlg.show();
                                    Log.d("infoWindow", " 정보창 클릭 됨 ");
                                    return true;
                            };
                                for (LatLng markerPosition : markersPosition) {
                                    String makertitle = title[Index];
                                    InfoWindow infoWindow = new InfoWindow();
                                    infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(getApplicationContext()) {
                                        @NonNull
                                        @Override
                                        public CharSequence getText(@NonNull InfoWindow infoWindow) {
                                            return makertitle;
                                        }
                                    });
                                    infoWindow.setTag(Index);
                                    infoWindow.setOnClickListener(listener);
                                    Index++;
                                    infoWindow.setPosition(markerPosition);
                                    infoWindow.open(mNaverMap);
                                }


                                for(int i = 0; i < display; i++) {
                                    Log.d("maker", " mapx :" + mapx[i] + " mapy :" + mapy[i]);
                                }
                            }catch (InterruptedException e){
                                Log.d("IOException:", e.toString());
                            }
                        }
                    });
                    //flag=true;


                } catch (Exception e) {
                    Log.d(TAG, "error : " + e);
                }

            }
        }.start();

    }

    // 현재 좌표를 도로명 주소로 변경----------------------------------------------------------------------------------------------------------------------------
    public void Reversegeododing(final double latitude, final double longitude) { // 검색어 = searchObject로 ;
        final String clientId = "ijv9rapnei";//애플리케이션 클라이언트 아이디값";
        final String clientSecret = "AMcP25rWcGuckuKB6hzNMNHJLqtAIIKKjdOqhLCZ";//애플리케이션 클라이언트 시크릿값";
        final int display = 5; // 보여지는 검색결과의 수

        // 네트워크 연결은 Thread 생성 필요
        new Thread() {

            @Override
            public void run() {
                try {
                    //String text = URLEncoder.encode(searchObject, "UTF-8");
                    String lat = Double.toString(latitude);
                    String lon = Double.toString(longitude);
                    String apiURL = "https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?coords=" + lon + "," + lat + "&orders=roadaddr&output=json"; // json 결과
                    // Json 형태로 결과값을 받아옴.
                    URL url = new URL(apiURL);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
                    con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
                    con.connect();

                    int responseCode = con.getResponseCode();
                    BufferedReader br;  // 버퍼를 활용한 입력

                    if (responseCode == 200) { // 정상 호출
                        br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    } else {  // 에러 발생
                        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                    }

                    StringBuilder searchResult = new StringBuilder();
                    // String을 연산하면 메모리 할당과 해제를 발생시키며 성능적으로 좋지 않음
                    // 그래서 나온게 StringBuilder 문자열을 더할 때 새로운 객체를 생성하는 것이 아니라 기존 데이터에 더하는 방식
                    String inputLine;
                    //readLine()은 개행문자가 포함되어야 내부 blocking이 풀리면서 wihle문이 실행한다는 것이다.
                    //스트림에서의 개행문자는 "\r\n"이 개행문자다.
                    while ((inputLine = br.readLine()) != null) {
                        searchResult.append(inputLine + "\n");

                    }
                    br.close();
                    con.disconnect();

                    String data = searchResult.toString();
                    String[] array;
                    array = data.split("\"");


                    int count = 0;
                    for (int i = array.length - 1; i >= 0; i--) {
                        if (array[i].equals("name")) {
                            road.append(array[i + 2].replaceAll("<[^>]*>", " "));
                            break;
                        }
                    }
                    //road.append(" 일식");
                    // for (int i = 0; i < array.length; i++) {
                    Log.d(TAG, "name잘나오니: " + road);
                    // title[0], link[0], bloggername[0] 등 인덱스 값에 맞게 검색결과를 변수화하였다.
                    //  }
                } catch (Exception e) {
                    Log.d(TAG, "error : " + e);
                }

            }
        }.start();

    }

}
