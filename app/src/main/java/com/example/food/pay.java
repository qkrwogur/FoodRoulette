package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class pay extends AppCompatActivity {

    EditText edit1,edit2;
    Button btn1;
    TextView text;
    int person;
    int money;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        edit1=(EditText)findViewById(R.id.payEdit1);
        edit2=(EditText)findViewById(R.id.payEdit2);
        btn1=(Button)findViewById(R.id.payBtn1);
        text=(TextView)findViewById(R.id.payText);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((CheckNumber(edit1.getText().toString())) && (CheckNumber(edit2.getText().toString()))){
                    person=Integer.parseInt(edit1.getText().toString());
                    money=Integer.parseInt(edit2.getText().toString());
                    result=Float.toString(money/person);
                    text.setText("인당 "+result+"입니다.");
                }else{
                    Toast.makeText(getApplicationContext(),"다시 입력 해주세요",Toast.LENGTH_SHORT).show();
                }

            }
        });




    }

    public boolean CheckNumber(String str){
        char check;

        if(str.equals(""))
        {
            //문자열이 공백인지 확인
            return false;
        }

        for(int i = 0; i<str.length(); i++){
            check = str.charAt(i);
            if( check < 48 || check > 58)
            {
                //해당 char값이 숫자가 아닐 경우
                return false;
            }

        }
        return true;
    }
}