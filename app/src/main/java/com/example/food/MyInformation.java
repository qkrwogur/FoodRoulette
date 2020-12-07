package com.example.food;

import android.app.Activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MyInformation extends Activity {
    private TextView ID,Password,name,phone;
    @Override


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myinformation);
        ID = findViewById(R.id.ordId);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.ordPhone);

        SessionManagement sessionManagement = new SessionManagement(MyInformation.this);
        String myUserID = sessionManagement.getSession();

        Response.Listener<String> reponseListener =new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success =jsonObject.getBoolean("success");
                    if(success){

                        String userPhone = jsonObject.getString("userPhone");
                        String userName = jsonObject.getString("userName");



                        ID.setText(myUserID);
                        name.setText(userName);
                        phone.setText(userPhone);

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"로그인에 실패",Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        MyinformationRequest myinformationRequest =new MyinformationRequest(myUserID,reponseListener);
        RequestQueue queue = Volley.newRequestQueue(MyInformation.this);
        queue.add(myinformationRequest);



        //Password.setText(userPass);
    }




}



