package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button roulette1;
    Button map;
    Button ladder;
    Button korea, japan, chain, western, flour, alone, delivery, asia, fast, latenihgt, cafe, pig,steamedsoup,box,chicken,pizza,all;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roulette1=(Button)findViewById(R.id.roulette1);
        map=(Button)findViewById(R.id.map);
        ladder=(Button)findViewById(R.id.ladder1);
        korea=(Button)findViewById(R.id.korea);
        japan=(Button)findViewById(R.id.japan);
        chain=(Button)findViewById(R.id.chain);
        western=(Button)findViewById(R.id.western);
        flour=(Button)findViewById(R.id.flour);
        alone=(Button)findViewById(R.id.alone);
        delivery=(Button)findViewById(R.id.delivery);
        asia=(Button)findViewById(R.id.asia);
        fast=(Button)findViewById(R.id.fast);
        latenihgt=(Button)findViewById(R.id.latenihgt);
        cafe=(Button)findViewById(R.id.cafe);
        pig=(Button)findViewById(R.id.pig);
        steamedsoup=(Button)findViewById(R.id.steamedsoup);
        box=(Button)findViewById(R.id.box);
        chicken=(Button)findViewById(R.id.chicken);
        pizza=(Button)findViewById(R.id.pizza);
        all=(Button)findViewById(R.id.all);


        roulette1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Roulette.class);
                startActivity(intent);
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),test.class);
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
                Intent intent=new Intent(getApplicationContext(),test.class);
                intent.putExtra("category"," 한식");
                startActivity(intent);
            }
        });
        japan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),test.class);
                intent.putExtra("category"," 일식");
                startActivity(intent);
            }
        });
        chain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),test.class);
                intent.putExtra("category"," 중식");
                startActivity(intent);
            }
        });
        western.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),test.class);
                intent.putExtra("category"," 양식");
                startActivity(intent);
            }
        });
        flour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),test.class);
                intent.putExtra("category"," 분식");
                startActivity(intent);
            }
        });
        alone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),test.class);
                intent.putExtra("category"," 혼밥");
                startActivity(intent);
            }
        });
        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),test.class);
                intent.putExtra("category"," 배달");
                startActivity(intent);
            }
        });
        asia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),test.class);
                intent.putExtra("category"," 아시아");
                startActivity(intent);
            }
        });
        fast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),test.class);
                intent.putExtra("category"," 햄버거");
                startActivity(intent);
            }
        });
        latenihgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),test.class);
                intent.putExtra("category"," 야식");
                startActivity(intent);
            }
        });
        cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),test.class);
                intent.putExtra("category"," 카페");
                startActivity(intent);
            }
        });
        pig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),test.class);
                intent.putExtra("category"," 족발");
                startActivity(intent);
            }
        });
        steamedsoup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),test.class);
                intent.putExtra("category"," 찜");
                startActivity(intent);
            }
        });
        box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),test.class);
                intent.putExtra("category"," 도시락");
                startActivity(intent);
            }
        });
        chicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),test.class);
                intent.putExtra("category"," 치킨");
                startActivity(intent);
            }
        });
        pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),test.class);
                intent.putExtra("category"," 피자");
                startActivity(intent);
            }
        });
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),test.class);
                intent.putExtra("category"," 식당");
                startActivity(intent);
            }
        });
    }
}