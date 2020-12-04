package com.example.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ReviewWrite extends Activity {
    TextView rv_write_title;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_write);
        rv_write_title=(TextView)findViewById(R.id.rv_write_title);
        String title= new String();
        Intent titleintent = getIntent();
        title = titleintent.getStringExtra("title");
        rv_write_title.setText(title);
    }
}
