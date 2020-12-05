package com.example.food;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ReviewWriteRequest extends StringRequest {
    //서버 URL 연결 (PHP파일 연결)
    final static private String URL = "http://food1116.dothome.co.kr/ReviewWriteRequest.php";
    private Map<String,String> map;

    public ReviewWriteRequest(String store, String id, int taste, int atmosphere, int price, int cleanliness , int volume, String reviewTest, Response.Listener<String> listener){
        super(Method.POST,URL,listener,null);

        map =new HashMap<>();
        map.put("store",store);
        map.put("id",id);
        map.put("taste",taste+"");
        map.put("atmosphere",atmosphere+"");
        map.put("price",price+"");
        map.put("cleanliness",cleanliness+"");
        map.put("volume",volume+"");
        map.put("reviewTest",reviewTest);

        //int 형은 user+""형태로 하면 된다.

    }

    //ctrl + o getParams
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}