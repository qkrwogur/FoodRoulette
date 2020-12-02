package com.example.food;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Review extends Activity {
    String[] test={"1","2","3","4","5","6","7","8","9","10"};
    private LinearLayout containertalbe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);
        containertalbe=(LinearLayout)findViewById(R.id.rv_Reviewtable);
        for (int i=0;i< test.length;i++){
            Reviewlist n_layout= new Reviewlist(getApplicationContext());
            containertalbe.addView(n_layout);
        }
    }
}
