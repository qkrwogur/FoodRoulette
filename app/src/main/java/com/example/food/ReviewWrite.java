package com.example.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ReviewWrite extends Activity {
    TextView rv_write_title;
    Button rv_write_end;
    String title= new String();
    RatingBar RC_taste,RC_atmosphere,RC_cleanliness,RC_volume,RC_price;
    int taste,atmosphere,cleanliness,volume,price;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_write);
        rv_write_title=(TextView)findViewById(R.id.rv_write_title);
        rv_write_end=(Button)findViewById(R.id.rv_write_end);
        Intent titleintent = getIntent();
        title = titleintent.getStringExtra("title");
        rv_write_title.setText(title);


        RC_taste=(RatingBar)findViewById(R.id.RC_taste);
        RC_atmosphere=(RatingBar)findViewById(R.id.RC_atmosphere);
        RC_cleanliness=(RatingBar)findViewById(R.id.RC_cleanliness);
        RC_volume=(RatingBar)findViewById(R.id.RC_volume);
        RC_price=(RatingBar)findViewById(R.id.RC_price);

        taste=(int)RC_taste.getRating();



        rv_write_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),Integer.toString(taste), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Review.class);
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });
    }
}
