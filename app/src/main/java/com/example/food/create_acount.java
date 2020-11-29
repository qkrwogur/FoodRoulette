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

public class create_acount extends Activity {

    private EditText idInput,passwordInput,nameInput,phoneInput;
    private Button btnJoin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_acount);

        idInput =findViewById(R.id.idInput);
        passwordInput =findViewById(R.id.passwordInput);
        nameInput = findViewById(R.id.nameInput);
        phoneInput = findViewById(R.id.phoneInput);
        btnJoin = findViewById(R.id.btnJoin);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //입력된 값을 get 한다.
                String userID = idInput.getText().toString();
                String userPassword = passwordInput.getText().toString();
                String userName = nameInput.getText().toString();
                String userPhone = phoneInput.getText().toString();

                Response.Listener<String> reponseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success =jsonObject.getBoolean("success");
                            if(success){
                                Toast.makeText(getApplicationContext(),"회원 등록의 성공",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(create_acount.this,login.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"회원 등록의 실패",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                //서버로 volly 사용 하여 요청
                RegisterRequest registerRequest = new RegisterRequest(userID,userPassword,userName,userPhone,reponseListener);
                RequestQueue queue = Volley.newRequestQueue(create_acount.this);
                queue.add(registerRequest);
            }
        });
    }
}
