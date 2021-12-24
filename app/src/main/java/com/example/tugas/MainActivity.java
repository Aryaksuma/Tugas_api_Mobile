package com.example.tugas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import com.example.tugas.model.DataWebFremwork;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
        final String linkAPI = "https://ewinsutriandi.github.io/mockapi/web_framework.json";

        ArrayList<DataWebFremwork> dataFrameworks = new ArrayList();
        JSONObject dataFrameworkAPI;
        ListView listview;
        FloatingActionButton btnRefresh;
        ProgressBar loadingIndicator;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            btnRefresh = findViewById(R.id.btnRefresh);
            btnRefresh.setOnClickListener(view -> getDataFramework());
            loadingIndicator = findViewById(R.id.loadingView);
            getDataFramework();
        }

        void setupListview () {
            listview = findViewById(R.id.listView);
            AdapterWebFremwork adapter = new AdapterWebFremwork(this, dataFrameworks);
            listview.setAdapter(adapter);
            listview.setOnItemClickListener(onItemClick);
        }

        private AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataWebFremwork fSELECTED = dataFrameworks.get(position);
                Intent intent = new Intent(MainActivity.this, DetailWebFremwork.class);
                intent.putExtra("FRAMEWORK_SELECTED", fSELECTED);
                startActivity(intent);
            }
        };

        void getDataFramework() {
            dataFrameworks.clear();
            loadingIndicator.setVisibility(View.VISIBLE);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, linkAPI, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            dataFrameworkAPI = response;
                            refreshView();
                        }

                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("error", error.toString());
                            loadingIndicator.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, "Erorr: No Connections, Please Check Your Connections", Toast.LENGTH_LONG).show();
                        }
                    });

            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(jsonObjectRequest);
            setupListview();
        }

        void refreshView() {
            Iterator<String> key = dataFrameworkAPI.keys();
            while(key.hasNext()) {
                String nameFramework = key.next();
                try {
                    JSONObject data = dataFrameworkAPI.getJSONObject(nameFramework);
                    String des = data.getString("description");
                    String author = data.getString("original_author");
                    String official_web = data.getString("official_website");
                    String img = data.getString("logo");
                    dataFrameworks.add(new DataWebFremwork(nameFramework, author, official_web, des, img));
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            loadingIndicator.setVisibility(View.GONE);
            setupListview();
        }

    }