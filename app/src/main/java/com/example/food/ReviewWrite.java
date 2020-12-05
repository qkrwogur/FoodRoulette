package com.example.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ReviewWrite extends Activity {
    TextView rv_write_title;
    Button rv_write_end;
    String title= new String();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_write);
        rv_write_title=(TextView)findViewById(R.id.rv_write_title);
        rv_write_end=(Button)findViewById(R.id.rv_write_end);
        Intent titleintent = getIntent();
        title = titleintent.getStringExtra("title");
        rv_write_title.setText(title);
        rv_write_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Review.class);
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });
    }
}
