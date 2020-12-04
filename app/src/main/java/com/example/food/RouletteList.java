package com.example.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class RouletteList extends Activity {
    ArrayList<String> selectedItems = new ArrayList<>();
    private LinearLayout containertalbe;
    int select;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roulettelist);
        ListView roulette_LV =(ListView)findViewById(R.id.roulette_LV);
        roulette_LV.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        String[] items={"한식","일식","양식","중식",
                "족발, 보쌈","찜, 탕","도시락","패스트푸드","분식","치킨","피자","아시아"};
        select=1;

        containertalbe=(LinearLayout)findViewById(R.id.roulette_screen);
        RouletteFree n_layout= new RouletteFree(getApplicationContext());
        containertalbe.addView(n_layout);

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, R.layout.roulettelist_check,R.id.roulette_CH,items);
        roulette_LV.setAdapter(adapter);
        roulette_LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem=((TextView)view).getText().toString();
                if(selectedItems.contains(selectedItem)){
                    selectedItems.remove(selectedItem);
                }
                else
                    selectedItems.add(selectedItem);
            }
        });
    }
    public void showSelectedItems(View view){
        String[] items={};
        if(selectedItems.size()>1 && selectedItems.size()<7) {
            int i=0;
            Intent intent = new Intent(getApplicationContext(),Roulette.class);
            items=selectedItems.toArray(new String[selectedItems.size()]);
            intent.putExtra("list",items);
            startActivity(intent);
            Toast.makeText(this, "선택 갯수 : " + selectedItems.size(), Toast.LENGTH_LONG).show();
        }else
            Toast.makeText(this, "선택 범위는 2~6개 입니다.", Toast.LENGTH_LONG).show();
    }
    public void freeClick(View view) {
    }
}
