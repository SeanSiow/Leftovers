package com.example.testpuppyapi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button);
        final SearchView search = findViewById(R.id.searchView);
        final TextView textView = findViewById(R.id.textView);

        final RequestQueue queue = Volley.newRequestQueue(this);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Must be in the format ingredient1,ingredient2
                String ingredients = search.getQuery().toString();

                String url = "http://www.recipepuppy.com/api/?i=" + ingredients;

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                textView.setText(response);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                textView.setText(error.toString());
                            }
                });

                queue.add(stringRequest);
            }
        });
    }
}