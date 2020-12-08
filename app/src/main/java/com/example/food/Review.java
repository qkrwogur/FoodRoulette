package com.example.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.geometry.Tm128;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.util.FusedLocationSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Review extends AppCompatActivity implements OnMapReadyCallback {
    String[] test={"1","2","3","4","5","6","7","8","9","10"};
    private LinearLayout containertalbe;
    Button rv_write;    // 리뷰 작성 버튼
    TextView rv_title, rv_address;  // 가게 이름, 도로명 주소 텍스트뷰
    private NaverMap mNaverMap;
    private FusedLocationSource mLocationSource;
    private static final int PERMISSION_REQUEST_CODE = 100;
    String get_title,roadaddress;
    LatLng latLng;
    CameraPosition cameraPosition;
    RatingBar rv_Rating;
    String[] store_list={"서대문꼼장어"};
    String[] id_list={"test"};
    String[] reviewText_list={"test"};
    int[] taste_list={5};
    int[] atmosphere_list={5};
    int[] price_list={5};
    int[] cleanliness_list={5};
    int[] volume_list={5};
    private static final String showUrl = "http://food1116.dothome.co.kr/ReviewScore.php";
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
        roadaddress=info_intent.getStringExtra("roadaddress");
        rv_address= (TextView)findViewById(R.id.rv_address);
        rv_address.setText(roadaddress);
        Tm128 tm128 = new Tm128(mx, my);
        latLng = tm128.toLatLng();
        rv_title.setText(get_title);
        rv_Rating=(RatingBar)findViewById(R.id.rv_Rating);

        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.rv_navermap);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.rv_navermap, mapFragment).commit();
        }
        getProducts();

        mapFragment.getMapAsync(this);

        /*for(int i=0; i<store_list.length; i++) {
            if (get_title.equals(store_list[i])){
                float avg=(float) (taste_list[i]+atmosphere_list[i]+price_list[i]+cleanliness_list[i]+ volume_list[i])/5;
                avg=avg/2;
                Reviewlist n_layout= new Reviewlist(getApplicationContext());
                TextView rv_Txt = (TextView)n_layout.findViewById(R.id.rv_Txt);
                rv_Txt.setText(id_list[i]);
                RatingBar rv_Rating=(RatingBar)n_layout.findViewById(R.id.rv_Rating);
                rv_Rating.setRating(avg);
                TextView rv_ReviewScore = (TextView)n_layout.findViewById(R.id.rv_ReviewScore);
                rv_ReviewScore.setText(Float.toString(avg));
                TextView rv_ReviewTEXT=(TextView)n_layout.findViewById(R.id.rv_ReviewTEXT);
                rv_ReviewTEXT.setText(reviewText_list[i]);
                containertalbe.addView(n_layout);
            }
        }*/
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
    private void getProducts(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, showUrl,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            store_list=new String[array.length()];
                            taste_list=new int[array.length()];
                            atmosphere_list=new int[array.length()];
                            price_list=new int[array.length()];
                            cleanliness_list=new int[array.length()];
                            volume_list=new int[array.length()];
                            id_list=new String[array.length()];
                            reviewText_list=new String[array.length()];
                            float Rating=0;
                            int count=0;
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                String store = object.getString("store");
                                String id = object.getString("id");
                                int taste = object.getInt("taste");
                                int atmosphere = object.getInt("atmosphere");
                                int price = object.getInt("price");
                                int cleanliness = object.getInt("cleanliness");
                                int volume = object.getInt("volume");
                                String reviewText = object.getString("reviewText");
                                id_list[i]=id;
                                store_list[i]=store;
                                taste_list[i]=taste;
                                atmosphere_list[i]=atmosphere;
                                price_list[i]=price;
                                cleanliness_list[i]=cleanliness;
                                volume_list[i]=volume;
                                reviewText_list[i]=reviewText;

                                if (get_title.equals(store_list[i])){
                                    float avg=(float) (taste_list[i]+atmosphere_list[i]+price_list[i]+cleanliness_list[i]+ volume_list[i])/5;
                                    avg=avg/2;
                                    Reviewlist n_layout= new Reviewlist(getApplicationContext());
                                    TextView rv_Txt = (TextView)n_layout.findViewById(R.id.rv_Txt);
                                    rv_Txt.setText(id_list[i]);
                                    RatingBar rv_Rating=(RatingBar)n_layout.findViewById(R.id.rv_Rating);
                                    rv_Rating.setRating(avg);
                                    TextView rv_ReviewScore = (TextView)n_layout.findViewById(R.id.rv_ReviewScore);
                                    rv_ReviewScore.setText(Float.toString(avg));
                                    TextView rv_ReviewTEXT=(TextView)n_layout.findViewById(R.id.rv_ReviewTEXT);
                                    rv_ReviewTEXT.setText(reviewText_list[i]);
                                    containertalbe.addView(n_layout);
                                    Rating=Rating+avg;
                                    count++;
                                }
                                Log.d("data",store_list[i]);
                            }
                            Rating=Rating/count;
                            rv_Rating.setRating(Rating);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(Review.this).add(stringRequest);



    }
}
