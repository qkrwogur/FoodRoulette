package com.example.food;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class StarRMRequest extends StringRequest {
    //서버 URL 연결 (PHP파일 연결)
    final static private String URL = "http://food1116.dothome.co.kr/StarRM.php";
    private Map<String,String> map;

    public StarRMRequest(String id, String store, Response.Listener<String> listener){
        super(Method.POST,URL,listener,null);

        map =new HashMap<>();
        map.put("id",id);
        map.put("store",store);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}