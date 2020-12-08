package com.example.food;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.UiThread;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouletteList extends Activity {
    ArrayList<String> selectedItems = new ArrayList<>();
  //  private LinearLayout containertalbe;
    int select;
    LinearLayout category;
    LinearLayout free;
    EditText Edt1,Edt2,Edt3,Edt4,Edt5,Edt6;
    String[] items={"한식","일식","양식","중식",
            "족발, 보쌈","찜, 탕","도시락","패스트푸드","분식","치킨","피자","아시아"};
    String[] star_items={};
    String[] star_road={};
    String[] star_mapx={};
    String[] star_mapy={};
    String[] star_id={};
    private static final String showUrl = "http://food1116.dothome.co.kr/starsearchAll.php";
    RequestQueue requestQueue;
    ListView roulette_LV;
    String userID;
    ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roulettelist);

        roulette_LV =(ListView)findViewById(R.id.roulette_LV);
        roulette_LV.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        SessionManagement sessionManagement = new SessionManagement(RouletteList.this);
        userID = sessionManagement.getSession();

        select=1;
        category = (LinearLayout)findViewById(R.id.category);
        free = (LinearLayout)findViewById(R.id.free);
        category.setVisibility(View.INVISIBLE);
        free.setVisibility(View.VISIBLE);

        Edt1=(EditText)findViewById(R.id.roulette_free_Edt1);
        Edt2=(EditText)findViewById(R.id.roulette_free_Edt2);
        Edt3=(EditText)findViewById(R.id.roulette_free_Edt3);
        Edt4=(EditText)findViewById(R.id.roulette_free_Edt4);
        Edt5=(EditText)findViewById(R.id.roulette_free_Edt5);
        Edt6=(EditText)findViewById(R.id.roulette_free_Edt6);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        getProducts();
        Toast.makeText(getApplicationContext(),userID,Toast.LENGTH_SHORT).show();
        adapter=new ArrayAdapter<>(this, R.layout.roulettelist_check,R.id.roulette_CH,items);
        roulette_LV.setAdapter(adapter);
        roulette_LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem=((TextView)view).getText().toString();
                if(selectedItems.contains(selectedItem)){
                    selectedItems.remove(selectedItem);
                }
                else
                    selectedItems.add(selectedItem);
            }
        });
    }
    public void showSelectedItems(View view){
        String[] items={};
        if (select==1){
            String[] ed=new String[6];

            int count=0;
            ed[0]=Edt1.getText().toString();
            ed[1]=Edt2.getText().toString();
            ed[2]=Edt3.getText().toString();
            ed[3]=Edt4.getText().toString();
            ed[4]=Edt5.getText().toString();
            ed[5]=Edt6.getText().toString();
            for(int i=0;i<6;i++){
                if(ed[i].length()==0){}
                else{count++;}
            }
            items=new String[count];
            count=0;
            for(int i=0;i<6;i++){
                if(ed[i].length()==0){}
                else{
                    items[count]=ed[i];
                    count++;
                }
            }
            if (count>1&&count<7) {
                Intent intent = new Intent(getApplicationContext(), Roulette.class);
                intent.putExtra("list", items);
                intent.putExtra("select",select);
                startActivity(intent);
                Toast.makeText(this, "선택 갯수 : " + selectedItems.size(), Toast.LENGTH_LONG).show();
            }else
                Toast.makeText(this, "선택 범위는 2~6개 입니다.", Toast.LENGTH_LONG).show();
        }else if(select==2){
            if (selectedItems.size() > 1 && selectedItems.size() < 7) {
                Intent intent = new Intent(getApplicationContext(), Roulette.class);
                items = selectedItems.toArray(new String[selectedItems.size()]);
                intent.putExtra("list", items);
                intent.putExtra("select",select);
                startActivity(intent);
                Toast.makeText(this, "선택 갯수 : " + selectedItems.size(), Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(this, "선택 범위는 2~6개 입니다.", Toast.LENGTH_LONG).show();
        }else{
            if (selectedItems.size() > 1 && selectedItems.size() < 7) {
                Intent intent = new Intent(getApplicationContext(), Roulette.class);
                items = selectedItems.toArray(new String[selectedItems.size()]);
                intent.putExtra("list", items);
                intent.putExtra("select",select);
                intent.putExtra("road",star_road);
                intent.putExtra("mapx",star_mapx);
                intent.putExtra("mapy",star_mapy);
                startActivity(intent);
                Toast.makeText(this, "선택 갯수 : " + selectedItems.size(), Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(this, "선택 범위는 2~6개 입니다.", Toast.LENGTH_LONG).show();
        }
    }
    public void freeClick(View view) {
        select=1;
        category.setVisibility(View.INVISIBLE);
        free.setVisibility(View.VISIBLE);
    }
    public void categoryClick(View view) {
        select=2;
        items= new String[]{"한식", "일식", "양식", "중식",
                "족발, 보쌈", "찜, 탕", "도시락", "패스트푸드", "분식", "치킨", "피자", "아시아"};
        adapter=new ArrayAdapter<>(this, R.layout.roulettelist_check,R.id.roulette_CH,items);
        roulette_LV.setAdapter(adapter);
        category.setVisibility(View.VISIBLE);
        free.setVisibility(View.INVISIBLE);

    }
    public void starClick(View view) {
        select = 3;
        adapter=new ArrayAdapter<>(this, R.layout.roulettelist_check,R.id.roulette_CH,star_items);
        roulette_LV.setAdapter(adapter);

        category.setVisibility(View.VISIBLE);
        free.setVisibility(View.INVISIBLE);
    }
    private void getProducts(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, showUrl,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            int count = 0;
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                String id = object.getString("id");
                                if(id.equals(userID)){
                                    count++;
                                    Log.d("item =", "ok");
                                }
                                //Toast.makeText(getApplicationContext(),items[i],Toast.LENGTH_SHORT).show();
                            }
                            star_items = new String[count];
                            star_road = new String[count];
                            star_mapx = new String[count];
                            star_mapy = new String[count];
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                String id = object.getString("id");
                                String store = object.getString("store");
                                String road = object.getString("road");
                                String mapx = object.getString("mapx");
                                String mapy = object.getString("mapy");
                                if(id.equals(userID)){
                                    star_items[i] = store;
                                    star_road[i] = road;
                                    star_mapx[i] = mapx;
                                    star_mapy[i] = mapy;
                                    Log.d("item =", userID + ", userid :"+id);
                                }
                                    //Toast.makeText(getApplicationContext(),items[i],Toast.LENGTH_SHORT).show();
                            }

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
        Volley.newRequestQueue(RouletteList.this).add(stringRequest);

    }
}

