package com.example.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Detail extends Activity {
    TextView txttitle,txtlink,txtdesc,txtaddress,txtroad;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        txttitle = (TextView)findViewById(R.id.txttitle);
        txtlink = (TextView)findViewById(R.id.txtlink);
        txtdesc = (TextView)findViewById(R.id.txtdesc);
        txtaddress = (TextView)findViewById(R.id.txtaddress);
        txtroad = (TextView)findViewById(R.id.txtroad);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String desc = intent.getStringExtra("description");
        String link = intent.getStringExtra("link");
        String address = intent.getStringExtra("address");
        String road = intent.getStringExtra("roadAddress");
        txttitle.setText(title);
        txtlink.setText(link);
        txtdesc.setText(desc);
        txtaddress.setText(address);
        txtroad.setText(road);
    }
}
