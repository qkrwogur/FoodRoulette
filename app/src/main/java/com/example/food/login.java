package com.example.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class login extends Activity {
    private EditText id,password;
    private Button loginBtn,goInput;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        id = findViewById(R.id.id);
        password =findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        goInput = findViewById(R.id.goInput);
        //회원 가입 이동
        goInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),create_acount.class);
                startActivity(intent);
            }
        });
        //로그인
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = id.getText().toString();
                String userPass = password.getText().toString();
                //로그인

                Response.Listener<String> reponseListener =new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success =jsonObject.getBoolean("success");
                            if(success){
                                String userID = jsonObject.getString("userID");
                                String userPass = jsonObject.getString("userPassword");
                                Toast.makeText(getApplicationContext(),"로그인에 성공",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(login.this,MyInformation.class);
                                intent.putExtra("userID",userID);
                                intent.putExtra("userPass",userPass);
                                startActivity(intent);
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
                LoginRequest loginRequest =new LoginRequest(userID,userPass,reponseListener);
                RequestQueue queue = Volley.newRequestQueue(login.this);
                queue.add(loginRequest);
            }
        });
    }
}
