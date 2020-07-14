package com.example.json_volley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public static ImageView myimage;
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    public RecyclerView r1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r1=(RecyclerView)findViewById(R.id.recyclerView);
        mQueue= Volley.newRequestQueue(this);
        jsonParse();

    }
    private void jsonParse(){
        String url = " https://newsapi.org/v2/top-headlines?country=us&apiKey=27e64574c3c64573afbea511eb0e65d2";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            ArrayList <ModelClass> items = new ArrayList <>();
                            JSONArray jsonArray = response.getJSONArray("articles");
                              for(int i=0;i<jsonArray.length();i++){
                                JSONObject cases = jsonArray.getJSONObject(i);
                                  String title = cases.getString("title");
                                  String url = cases.getString("urlToImage");


                                  items.add(new ModelClass(title,url));
//                                  String a1 = cases.getString("dailyconfirmed");
//                                  String a2 = cases.getString("dailydeceased");
//                                  String a3 = cases.getString("dailyrecovered");
//                                  String a4 = cases.getString("totalconfirmed");
//
//                                  String s1 = cases.getString("date");
//                                  mTextViewResult.append("dailyconfirmed :"+a1+"     dailydeceased :"+a2+
//                                          "      dailyrecovered:"+a3+"     totalconfirmed:"+a4+ "   age:" +
//                                          "     data:"+s1+"\n");
                            }
                           MyOwnAdapter adapter = new MyOwnAdapter(getApplicationContext(), items);
                            r1.setAdapter(adapter);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            r1.setLayoutManager(layoutManager);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
}


