package com.example.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MyInformation extends Activity {
    private TextView ID,Password,name,phone;
    @Override


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myinformation);
        ID = findViewById(R.id.ordId);
        Password = findViewById(R.id.showPassword);
        name = findViewById(R.id.showName);
        phone = findViewById(R.id.ordPhone);

        Intent intent =getIntent();
        String userID =  intent.getStringExtra("userID");
        String userPass =  intent.getStringExtra("userPass");

        ID.setText(userID);
        Password.setText(userPass);
    }
}
