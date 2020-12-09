package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton roulette1;
    ImageButton map;
    ImageButton ladder;
    ImageButton korea, japan, chain, western, flour, alone, delivery, asia, fast, latenihgt, cafe, pig,steamedsoup,box,chicken,pizza,all;
    ImageButton pay,logoutBtn,myinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");
        roulette1=(ImageButton)findViewById(R.id.roulette1);
        map=(ImageButton)findViewById(R.id.map);
        ladder=(ImageButton)findViewById(R.id.ladder1);
        korea=(ImageButton)findViewById(R.id.korea);
        japan=(ImageButton)findViewById(R.id.japan);
        chain=(ImageButton)findViewById(R.id.chain);
        western=(ImageButton)findViewById(R.id.western);
        flour=(ImageButton)findViewById(R.id.flour);
        alone=(ImageButton)findViewById(R.id.alone);
        delivery=(ImageButton)findViewById(R.id.delivery);
        asia=(ImageButton)findViewById(R.id.asia);
        fast=(ImageButton)findViewById(R.id.fast);
        latenihgt=(ImageButton)findViewById(R.id.latenihgt);
        cafe=(ImageButton)findViewById(R.id.cafe);
        pig=(ImageButton)findViewById(R.id.pig);
        steamedsoup=(ImageButton)findViewById(R.id.steamedsoup);
        box=(ImageButton)findViewById(R.id.box);
        chicken=(ImageButton)findViewById(R.id.chicken);
        pizza=(ImageButton)findViewById(R.id.pizza);
        all=(ImageButton)findViewById(R.id.all);
        pay =(ImageButton)findViewById(R.id.pay);
        logoutBtn=(ImageButton)findViewById(R.id.logoutBtn);
        myinfo=(ImageButton)findViewById(R.id.myinformation);


        roulette1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),RouletteList.class);
                startActivity(intent);
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Mappage.class);
                intent.putExtra("category"," 식당");
                startActivity(intent);
            }
        });
        ladder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ladder.class);
                startActivity(intent);
            }
        });
        korea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Mappage.class);
                intent.putExtra("category"," 한식");
                startActivity(intent);
            }
        });
        japan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Mappage.class);
                intent.putExtra("category"," 일식");
                startActivity(intent);
            }
        });
        chain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Mappage.class);
                intent.putExtra("category"," 중식");
                startActivity(intent);
            }
        });
        western.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Mappage.class);
                intent.putExtra("category"," 양식");
                startActivity(intent);
            }
        });
        flour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Mappage.class);
                intent.putExtra("category"," 분식");
                startActivity(intent);
            }
        });
        alone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Mappage.class);
                intent.putExtra("category"," 혼밥");
                startActivity(intent);
            }
        });
        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Mappage.class);
                intent.putExtra("category"," 배달");
                startActivity(intent);
            }
        });
        asia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Mappage.class);
                intent.putExtra("category"," 아시아");
                startActivity(intent);
            }
        });
        fast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Mappage.class);
                intent.putExtra("category"," 햄버거");
                startActivity(intent);
            }
        });
        latenihgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Mappage.class);
                intent.putExtra("category"," 야식");
                startActivity(intent);
            }
        });
        cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Mappage.class);
                intent.putExtra("category"," 카페");
                startActivity(intent);
            }
        });
        pig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Mappage.class);
                intent.putExtra("category"," 족발");
                startActivity(intent);
            }
        });
        steamedsoup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Mappage.class);
                intent.putExtra("category"," 찜");
                startActivity(intent);
            }
        });
        box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Mappage.class);
                intent.putExtra("category"," 도시락");
                startActivity(intent);
            }
        });
        chicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Mappage.class);
                intent.putExtra("category"," 치킨");
                startActivity(intent);
            }
        });
        pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Mappage.class);
                intent.putExtra("category"," 피자");
                startActivity(intent);
            }
        });
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Mappage.class);
                intent.putExtra("category"," 식당");
                startActivity(intent);
            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),pay.class);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
                sessionManagement.removeSession();

                moveToLogin();

            }
        });

        myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),MyInformation.class);
                startActivity(intent);

            }
        });


    }
    private void moveToLogin() {
        Intent intent = new Intent(MainActivity.this, login.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}