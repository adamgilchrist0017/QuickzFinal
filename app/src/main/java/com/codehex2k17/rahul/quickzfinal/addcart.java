package com.codehex2k17.rahul.quickzfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class addcart extends AppCompatActivity implements View.OnClickListener {




    private Button btn1;
    private Button btn2;
    private Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcart);
        btn1= (Button) findViewById(R.id.btn3);
        btn1.setOnClickListener(this);
        btn1.performClick();




    }



    private void addinfo() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.KEY_CARTURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(addcart.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(addcart.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Config.KEY_ID, cart.id);
                params.put(Config.KEY_NAME2, cart.Name);
                params.put(Config.KEY_PRICE2, cart.Finalprice);
                params.put(Config.KEY_NAMEUSER,login.name);
                params.put(Config.KEY_PHONE3,login.phone);
                return params;

            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public void onClick(View v) {
        if (v == btn1) {
            addinfo();
            buttonclick();
        }
    }
    private void buttonclick() {
        btn2 = (Button) findViewById(R.id.shop);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1 = new Intent(addcart.this, scan.class);
                startActivity(in1);
            }

        });
    }
}
