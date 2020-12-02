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
    ListView listView;
    String[] test={"1","2","3","4","5","6","7","8","9","10"};
    private LinearLayout container,containertalbe;

    private static final float FONT_SIZE=20;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);
        //container=(LinearLayout)findViewById(R.layout.rv_Review);
        containertalbe=(LinearLayout)findViewById(R.id.rv_Reviewtable);
        for (int i=0;i< test.length;i++){
            /*TextView rv_Text = new TextView(this);
            rv_Text.setText(test[i]);
            rv_Text.setTextSize(FONT_SIZE);
            rv_Text.setTextColor(Color.BLACK);
            //layout_width, layout_height, gravity 설정
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.CENTER;
            rv_Text.setLayoutParams(lp);
            //부모 뷰에 추가
            container.addView(rv_Text);
            ImageView star=new ImageView(this);
            star.setImageResource(R.drawable.ic_star);
            LinearLayout.LayoutParams starlp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            star.setLayoutParams(starlp);
            container.addView(star);*/
            Reviewlist n_layout= new Reviewlist(getApplicationContext());
            containertalbe.addView(n_layout);


        }
    }
}
