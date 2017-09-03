package com.codehex2k17.rahul.quickzfinal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextname;
    public static String name;
    private Button sign;
    private EditText editTextphone;
    public static String phone;
    public static String password;
    private Button register;
    private EditText editTextpassword;

    public ProgressDialog loading;
    private boolean loggedin = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextname = (EditText) findViewById(R.id.name);
        editTextphone = (EditText) findViewById(R.id.phone);
        editTextpassword = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);
        sign = (Button) findViewById(R.id.signin);
        sign.setOnClickListener(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }});
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);


        loggedin = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);

        if (loggedin) {
            Intent intent = new Intent(login.this, scan.class);
            startActivity(intent);
        }
    }

    private void login() {
        name = editTextname.getText().toString().trim();
        phone = editTextphone.getText().toString().trim();
        password = editTextpassword.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase(Config.LOGIN_SUCCESS)) {

                    Toast.makeText(login.this,"Successfully logged in",Toast.LENGTH_LONG).show();
                    Intent in1 = new Intent(login.this, scan.class);

                    startActivity(in1);

                } else {

                    Toast.makeText(login.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(login.this, "We're working on it.", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put(Config.KEY_NAMEUSER, name);
                params.put(Config.KEY_PHONE3, phone);
                params.put(Config.KEY_PASS3, password);


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public void onClick(View v) {
        login();


    }

    }

