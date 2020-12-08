package com.example.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ReviewWrite extends Activity {
    TextView rv_write_title;
    Button rv_write_end;
    String title = new String();
    RatingBar RC_taste,RC_atmosphere,RC_cleanliness,RC_volume,RC_price;
    EditText rv_write_Contents;
    int taste=1,atmosphere=1,cleanliness=1,volume=1,price=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_write);
        rv_write_title=(TextView)findViewById(R.id.rv_write_title);
        rv_write_end=(Button)findViewById(R.id.rv_write_end);
        Intent titleintent = getIntent();
        title = titleintent.getStringExtra("title");
        rv_write_title.setText(title);
        rv_write_Contents=(EditText)findViewById(R.id.rv_write_Contents);

        RC_taste=(RatingBar)findViewById(R.id.RC_taste);
        RC_atmosphere=(RatingBar)findViewById(R.id.RC_atmosphere);
        RC_cleanliness=(RatingBar)findViewById(R.id.RC_cleanliness);
        RC_volume=(RatingBar)findViewById(R.id.RC_volume);
        RC_price=(RatingBar)findViewById(R.id.RC_price);


        RC_taste.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (ratingBar.getRating()<0.5f){
                    ratingBar.setRating(0.5f);
                }
                taste=(int)(rating*2);
            }
        });
        RC_atmosphere.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (ratingBar.getRating()<0.5f){
                    ratingBar.setRating(0.5f);
                }
                atmosphere=(int)(rating*2);
            }
        });
        RC_cleanliness.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (ratingBar.getRating()<0.5f){
                    ratingBar.setRating(0.5f);
                }
                cleanliness=(int)(rating*2);
            }
        });
        RC_volume.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (ratingBar.getRating()<0.5f){
                    ratingBar.setRating(0.5f);
                }
                volume=(int)(rating*2);
            }
        });
        RC_price.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (ratingBar.getRating()<0.5f){
                    ratingBar.setRating(0.5f);
                }
                price=(int)(rating*2);
            }
        });

        rv_write_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Contents = new String();
                Contents = rv_write_Contents.getText().toString(); // 리뷰내용 저장
                Toast.makeText(getApplicationContext(),Contents, Toast.LENGTH_SHORT).show();

                Response.Listener<String> reponseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success =jsonObject.getBoolean("success");
                            if(success){
                                Toast.makeText(getApplicationContext(),"리뷰 작성 성공",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Review.class);
                                intent.putExtra("title",title);
                                startActivity(intent);

                            }
                            else{
                                Toast.makeText(getApplicationContext(),"실패",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                //서버로 volly 사용 하여 요청

                SessionManagement sessionManagement = new SessionManagement(ReviewWrite.this);

                String userID = sessionManagement.getSession();
                ReviewWriteRequest reviewWriteRequest = new ReviewWriteRequest(title,userID,taste,atmosphere,cleanliness,volume,price,Contents,reponseListener);
                RequestQueue queue = Volley.newRequestQueue(ReviewWrite.this);
                queue.add(reviewWriteRequest);
            }
        });
    }

}
