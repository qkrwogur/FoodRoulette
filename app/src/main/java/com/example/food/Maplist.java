package com.example.food;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class Maplist extends LinearLayout {
    public Maplist(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }
    public Maplist(Context context){
        super(context);
        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.foodlist_check,this,true);
    }
}
