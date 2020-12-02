package com.example.food;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import java.util.jar.Attributes;

public class Reviewlist extends LinearLayout {
    public Reviewlist(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }
    public Reviewlist(Context context){
        super(context);
        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.review_list,this,true);
    }
}
