package com.example.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class List extends Activity {
    Button btnreturn;
    EditText edtlist1,edtlist2,edtlist3,edtlist4,edtlist5,edtlist6;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀바 없애기
        setContentView(R.layout.list);
        Intent intent = getIntent();
        btnreturn=(Button)findViewById(R.id.btnreturn);
        edtlist1=(EditText)findViewById(R.id.edtlist1);
        edtlist2=(EditText)findViewById(R.id.edtlist2);
        edtlist3=(EditText)findViewById(R.id.edtlist3);
        edtlist4=(EditText)findViewById(R.id.edtlist4);
        edtlist5=(EditText)findViewById(R.id.edtlist5);
        edtlist6=(EditText)findViewById(R.id.edtlist6);



    }
    public void returnClick(View view)
    {
        String[] str= new String[6];
        Intent intent = new Intent();
        str[0]=edtlist1.getText().toString();
        str[1]=edtlist2.getText().toString();
        str[2]=edtlist3.getText().toString();
        str[3]=edtlist4.getText().toString();
        str[4]=edtlist5.getText().toString();
        str[5]=edtlist6.getText().toString();
        //데이터 전달하기
        intent.putExtra("list",str);
        setResult(RESULT_OK,intent);

        finish();

    }
}
