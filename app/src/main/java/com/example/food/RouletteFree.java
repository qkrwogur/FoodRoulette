package com.example.food;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class RouletteFree extends LinearLayout {
    public RouletteFree(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }
    public RouletteFree(Context context){
        super(context);
        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.roulettelist_free,this,true);
    }
}
