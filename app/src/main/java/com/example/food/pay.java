package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class pay extends AppCompatActivity {

    EditText edit1,edit2;
    Button btn1;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        edit1=(EditText)findViewById(R.id.payEdit1);
        edit2=(EditText)findViewById(R.id.payEdit2);

    }
}