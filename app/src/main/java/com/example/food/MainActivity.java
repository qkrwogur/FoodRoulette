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
    Button korea, japan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roulette1=(Button)findViewById(R.id.roulette1);
        map=(Button)findViewById(R.id.map);
        ladder=(Button)findViewById(R.id.ladder1);
        korea=(Button)findViewById(R.id.korea);
        japan=(Button)findViewById(R.id.japan);

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
    }
}