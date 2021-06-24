package com.example.newsfresh;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecycleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchData();
        adapter = new RecycleAdapter(this);
        recyclerView.setAdapter(adapter);

    }
    private void fetchData() {
        String url = "https://gnews.io/api/v4/top-headlines?token=37870d56f6de24f44cc2a65a4855b509&country=in&lang=en";
        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray newsJsonArray = response.getJSONArray("articles");
                                    ArrayList<News> newsArray = new ArrayList<>();
                                    for(int i=0;i<newsJsonArray.length();i++) {
                                        JSONObject newsJsonObject = newsJsonArray.getJSONObject(i);
                                        News news = new News(
                                                newsJsonObject.getString("title"),
                                                newsJsonObject.getString("content"),
                                                newsJsonObject.getString("url"),
                                                newsJsonObject.getString("image")
                                        );
                                        newsArray.add(news);
                                    }
                                    adapter.updateNews(newsArray);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }

                });
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}
