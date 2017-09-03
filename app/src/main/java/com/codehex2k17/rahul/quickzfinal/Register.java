package com.codehex2k17.rahul.quickzfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private Button register;
    public EditText name1;
    public EditText email1;
    public EditText phone1;
    public EditText pass1;
    public static String name;
    public static String email;
    public static String phone;
    public static  String pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = (Button) findViewById(R.id.register1);
        register.setOnClickListener(this);
        name1=(EditText)findViewById(R.id.name);
        email1=(EditText)findViewById(R.id.email);
        phone1=(EditText)findViewById(R.id.phone);
        pass1=(EditText)findViewById(R.id.password);
        name=name1.getText().toString();
        email=email1.getText().toString();
        phone=phone1.getText().toString();
        pass=pass1.getText().toString();




    }

    private void adddata() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.REG_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Register.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Config.KEY_NAME3, name);
                params.put(Config.KEY_EMAIL3, email);
                params.put(Config.KEY_PHONE3, phone);
                params.put(Config.KEY_PASS3,pass);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public void onClick(View v) {
        if (v == register) {
            name=name1.getText().toString();
            email=email1.getText().toString();
            phone=phone1.getText().toString();
            pass=pass1.getText().toString();
            adddata();
            Intent go=new Intent(Register.this,login.class);


            startActivity(go);
        }
    }
}















