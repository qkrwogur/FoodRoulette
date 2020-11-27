package com.example.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class Listpage extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodlist);
        ListView listView =(ListView)findViewById(R.id.listfood);
        String[] title= new String[]{};
        Intent intent = getIntent();
        title = intent.getStringArrayExtra("title");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, title); // 매개변수 리스트의 단순리스형식, 나열글자
        // 리스트뷰에 설정된 arrayadpter를 적용함
        listView.setAdapter(adapter); // 형식, 나열글자가 적용됨, 리스트뷰에 설정된 arrayadapter를 적용함

    }
}
