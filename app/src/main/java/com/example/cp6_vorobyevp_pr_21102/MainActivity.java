package com.example.cp6_vorobyevp_pr_21102;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView city, temp, condition;
    ImageView icon;
    ArrayList<Weather> wea = new ArrayList<>();
    WeatherAdapter adapter;
    RecyclerView recyclerView;

    private RequestQueue mqueue;
    private static final String API_KEY = "ad7d4ec8f33e459da2c105308232310";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        city = findViewById(R.id.city);
        temp = findViewById(R.id.temp);
        condition = findViewById(R.id.condition);
        icon = findViewById(R.id.icon);
        getData();
    }

    public void getData() {
        mqueue = Volley.newRequestQueue(this);
        String URL = "https://api.weatherapi.com/v1/forecast.json?key="+API_KEY+ "&q=Novosibirsk&days=7&aqi=no&alerts=no";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("mylog222222", "success");
                    JSONObject location = response.getJSONObject("location");
                    String name = location.getString("name");
                    JSONObject current = response.getJSONObject("current");
                    JSONObject cond = current.getJSONObject("condition");
                    String text = cond.getString("text");
                    String url = "https:" + cond.getString("icon");
                    Glide.with(getApplicationContext())
                            .load(url)
                            .fitCenter()
                            .into(icon);
                    condition.setText(text);
                    String temp_c = current.getString("temp_c");
                    temp.setText(temp_c);
                    city.setText(name);
                    JSONObject forecast = response.getJSONObject("forecast");
                    JSONArray forecastday = forecast.getJSONArray("forecastday");

                    for (int i = 0; i < forecastday.length(); i++) {
                        JSONObject forecastDayElement = forecastday.getJSONObject(i);
                        String date = forecastDayElement.getString("date");
                        JSONObject day = forecastDayElement.getJSONObject("day");
                        JSONObject condition = day.getJSONObject("condition");
                        url = "https:" + condition.getString("icon");
                        String maxtemp_c = day.getString("maxtemp_c");
                        wea.add(new Weather(date, maxtemp_c, url));
                    }
                    adapter = new WeatherAdapter(MainActivity.this, wea);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    Log.d("mylog222222", "error");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("mylog222222", error.getMessage());
            }
        });
        mqueue.add(request);
    }
}
