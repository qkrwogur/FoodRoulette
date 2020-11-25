package com.example.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class navertest {
    //String m_strNaverKey = "jp1w3bsYCw5vg6bzD2S4";
    //String m_strSearch = "한식";

    /*public void searchNaver(final String searchObject) { // 검색어 = searchObject로 ;
        final String clientId = "jp1w3bsYCw5vg6bzD2S4";//애플리케이션 클라이언트 아이디값";
        final String clientSecret = "3X23L0Crpr";//애플리케이션 클라이언트 시크릿값";
        final int display = 5; // 보여지는 검색결과의 수

        // 네트워크 연결은 Thread 생성 필요
        new Thread() {

            @Override
            public void run() {
                try {
                    String text = URLEncoder.encode(searchObject, "UTF-8");
                    String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text + "&display=" + display + "&"; // json 결과
                    // Json 형태로 결과값을 받아옴.
                    URL url = new URL(apiURL);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("X-Naver-Client-Id", clientId);
                    con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                    con.connect();

                    int responseCode = con.getResponseCode();
                    BufferedReader br;

                    if(responseCode==200) { // 정상 호출
                        br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    } else {  // 에러 발생
                        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                    }

                    StringBuilder searchResult = new StringBuilder();
                    String inputLine;
                    while ((inputLine = br.readLine()) != null) {
                        searchResult.append(inputLine + "\n");

                    }
                    br.close();
                    con.disconnect();

                    String data = searchResult.toString();
                    String[] array;
                    array = data.split("\"");
                    String[] title = new String[display];
                    String[] link = new String[display];
                    String[] description = new String[display];
                    String[] bloggername = new String[display];
                    String[] postdate = new String[display];

                    int k = 0;
                    for (int i = 0; i < array.length; i++) {
                        if (array[i].equals("title"))
                            title[k] = array[i + 2];
                        if (array[i].equals("link"))
                            link[k] = array[i + 2];
                        if (array[i].equals("description"))
                            description[k] = array[i + 2];
                        if (array[i].equals("bloggername"))
                            bloggername[k] = array[i + 2];
                        if (array[i].equals("postdate")) {
                            postdate[k] = array[i + 2];
                            k++;
                        }
                    }

                    Log.d(TAG, "title잘나오니: " + title[0] + title[1] + title[2]);
                    // title[0], link[0], bloggername[0] 등 인덱스 값에 맞게 검색결과를 변수화하였다.

                } catch (Exception e) {
                    Log.d(TAG, "error : " + e);
                }

            }
        }.start();

    }*/
/*    @Override
    public void run()
    {
        String strUrl = "http://openapi.naver.com/search?key=" + m_strNaverKey + "&target=local&query=" + m_strSearch
                + "&display=10&start=1";

        Log.d( "Search", "url=[" + strUrl + "]" );

​

        try
        {
            URL clsUrl = new URL( strUrl );
            InputStream clsStream = clsUrl.openStream( );

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = dbf.newDocumentBuilder();
            Document dc = parser.parse( clsStream );

            NodeList clsList = dc.getElementsByTagName( "item" );
            int iCount = clsList.getLength( );

            for( int i = 0; i < iCount; ++i )
            {
                Node clsNode = clsList.item( i );

                NodeList clsChildList = clsNode.getChildNodes( );
                int iChildCount = clsChildList.getLength( );

                SearchData clsData = new SearchData();

                for( int j = 0; j < iChildCount; ++j )
                {
                    Node clsChildNode = clsChildList.item( j );
                    String strName = clsChildNode.getNodeName( );

                    if( strName.equals( "title" ) )
                    {
                        clsData.m_strName = clsChildNode.getFirstChild( ).getNodeValue( );
                    }
                    else if( strName.equals( "address" ) )
                    {
                        clsData.m_strAddress = clsChildNode.getFirstChild( ).getNodeValue( );
                    }
                    else if( strName.equals( "mapx" ) )
                    {
                        clsData.m_strX = clsChildNode.getFirstChild( ).getNodeValue( );
                    }
                    else if( strName.equals( "mapy" ) )
                    {
                        clsData.m_strY = clsChildNode.getFirstChild( ).getNodeValue( );
                    }
                }

                Log.d( "Address", clsData.toString( ) );
            }

            clsStream.close( );
        }
        catch( Exception e )
        {
        }

    }

    class SearchData
    {
        String m_strName;
        String m_strAddress;
        String m_strX;
        String m_strY;

        public String toString()
        {
            return "name(" + m_strName + ") address(" + m_strAddress + ") x(" + m_strX + ") y(" + m_strY + ")";
        }
    }*/
/*
    public static void main(String[] args) throws Exception{
        String m_strNaverKey = "jp1w3bsYCw5vg6bzD2S4";
        String m_strSearch = "한식";
        String[] result_title={};
        String[] result_telephone={};
        String[] result_address={};
        m_strSearch=m_strSearch.replaceAll(" ","");
        m_strSearch= URLEncoder.encode(m_strSearch,"utf-8");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse( "http://openapi.naver.com/search?key=" + m_strNaverKey + "&target=local&query=" + m_strSearch
                + "&display=10&start=1"
        );
        NodeList list=doc.getElementsByTagName("channel");

        for(int i=0;i<list.getLength();i++){
            for (Node node=list.item(i).getFirstChild();node!=null;node=node.getNextSibling()){
                if (node.getNodeName().equals("title")){
                    System.out.println("-----title------");
                    System.out.println(node.getTextContent());
                }
                if (node.getNodeName().equals("lastBuildDate")){
                    System.out.println("-----lastBuildDate-----");
                    System.out.println(node.getTextContent()+"\n");

                }

                if (node.getNodeName().equals("item")){
                    for(Node node2 = node.getFirstChild();node2!=null;node2=node2.getNextSibling()){
                        if (node2.getNodeName().equals("title")){
                            result_title[i]=node2.getTextContent();
                        }else if(node2.getNodeName().equals("telephone")){
                            result_telephone[i]=node2.getTextContent();
                        }else if(node2.getNodeName().equals("address")){
                            result_address[i]=node2.getTextContent();
                        }

                    }
                }
            }
        }
        Intent intent = new Intent

    }
*/
}

