package com.example.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class RouletteList extends Activity {
    ArrayList<String> selectedItems = new ArrayList<>();
  //  private LinearLayout containertalbe;
    int select;
    LinearLayout category;
    LinearLayout free;
    EditText Edt1,Edt2,Edt3,Edt4,Edt5,Edt6;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roulettelist);
        String[] items={"한식","일식","양식","중식",
                "족발, 보쌈","찜, 탕","도시락","패스트푸드","분식","치킨","피자","아시아"};
        ListView roulette_LV =(ListView)findViewById(R.id.roulette_LV);
        roulette_LV.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        select=1;
        category = (LinearLayout)findViewById(R.id.category);
        free = (LinearLayout)findViewById(R.id.free);
        category.setVisibility(View.INVISIBLE);
        free.setVisibility(View.VISIBLE);

        Edt1=(EditText)findViewById(R.id.roulette_free_Edt1);
        Edt2=(EditText)findViewById(R.id.roulette_free_Edt2);
        Edt3=(EditText)findViewById(R.id.roulette_free_Edt3);
        Edt4=(EditText)findViewById(R.id.roulette_free_Edt4);
        Edt5=(EditText)findViewById(R.id.roulette_free_Edt5);
        Edt6=(EditText)findViewById(R.id.roulette_free_Edt6);

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
        if (select==1){
            String[] ed=new String[6];

            int count=0;
            ed[0]=Edt1.getText().toString();
            ed[1]=Edt2.getText().toString();
            ed[2]=Edt3.getText().toString();
            ed[3]=Edt4.getText().toString();
            ed[4]=Edt5.getText().toString();
            ed[5]=Edt6.getText().toString();
            for(int i=0;i<6;i++){
                if(ed[i].length()==0){}
                else{count++;}
            }
            items=new String[count];
            count=0;
            for(int i=0;i<6;i++){
                if(ed[i].length()==0){}
                else{
                    items[count]=ed[i];
                    count++;
                }
            }
            if (count>1&&count<7) {
                Intent intent = new Intent(getApplicationContext(), Roulette.class);
                intent.putExtra("list", items);
                startActivity(intent);
                Toast.makeText(this, "선택 갯수 : " + selectedItems.size(), Toast.LENGTH_LONG).show();
            }else
                Toast.makeText(this, "선택 범위는 2~6개 입니다.", Toast.LENGTH_LONG).show();
        }else {
            if (selectedItems.size() > 1 && selectedItems.size() < 7) {
                int i = 0;
                Intent intent = new Intent(getApplicationContext(), Roulette.class);
                items = selectedItems.toArray(new String[selectedItems.size()]);
                intent.putExtra("list", items);
                startActivity(intent);
                Toast.makeText(this, "선택 갯수 : " + selectedItems.size(), Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(this, "선택 범위는 2~6개 입니다.", Toast.LENGTH_LONG).show();
        }
    }
    public void freeClick(View view) {
        select=1;
        category.setVisibility(View.INVISIBLE);
        free.setVisibility(View.VISIBLE);
    }
    public void categoryClick(View view) {
        select=2;
        category.setVisibility(View.VISIBLE);
        free.setVisibility(View.INVISIBLE);
    }
    public void starClick(View view) {
        select=3;
        category.setVisibility(View.INVISIBLE);
        free.setVisibility(View.VISIBLE);
    }
}
