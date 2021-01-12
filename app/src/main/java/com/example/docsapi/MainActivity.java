package com.example.docsapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout=findViewById(R.id.new_document);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,NewDocument.class);
                startActivity(intent);
            }
        });

        String user_id="user1";
        final String url = "http://ec2-3-16-83-100.us-east-2.compute.amazonaws.com:8080/get-all-document/"+user_id;

        final List<Document> documents=new ArrayList<>();
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray=response.getJSONArray("document");
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                documents.add(new Document(jsonObject.getString("doc_id") ));
                            }
                            makelist(documents);
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this,""+e,Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,""+error,Toast.LENGTH_LONG).show();
                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
    public void makelist(final List<Document> list)
    {
        AdapterMain adapter=new AdapterMain(list,getApplication());
        recyclerView= (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter.setOnItemClickListener(new AdapterMain.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
    Intent intent=new Intent(MainActivity.this,GetDocument.class);
    intent.putExtra("doc_id",list.get(position).getDoc_id());
    startActivity(intent);
            }});
    }
    }