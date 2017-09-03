package com.codehex2k17.rahul.quickzfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class finalcart extends AppCompatActivity implements View.OnClickListener {
    private Button buttonget;
    private ListView listView;
    Button remove;
    double p;
    public static String data = null;
    public static String name=null;
    String s;
    String q;
    Button checkout;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalcart);
        buttonclick1();
        buttonget = (Button) findViewById(R.id.buttonGet);
        buttonget.setOnClickListener(this);
        buttonget.performClick();
        listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                data = (String) arg0.getItemAtPosition(arg2);
                if (data!=null) {
                    Toast.makeText(finalcart.this, "Item selected:" + data, Toast.LENGTH_SHORT).show();
                }

            }

        });

    }


    private void sendRequest() {
        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.KEY_RURL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(finalcart.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put(Config.KEY_NAMEUSER, login.name);
                params.put(Config.KEY_PHONE3, login.phone);
                return params;
            }
        };


            RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
            }

            private void showJSON(String json) {

                parsejson pj = new parsejson(json);
                pj.parsejson();
                TextView textView = (TextView) findViewById(R.id.textView);
                p = pj.Finalprice3;
                q = Double.toString(p);
                s = q;
                textView.setText(s);
                customlistview cl = new customlistview(this, parsejson.barcodeids, parsejson.getNames(), parsejson.finalprices);
                listView.setAdapter(cl);

            }


            @Override
            public void onClick(View v) {
                sendRequest();
                buttonclick1();
                buttonclick2();
            }



            private void buttonclick1() {
                checkout = (Button) findViewById(R.id.checkout);
                checkout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(p!=0) {
                            Intent in2 = new Intent(finalcart.this, payment.class);
                            in2.putExtra("amount", q);
                            startActivity(in2);
                        }
                        else{
                            Toast.makeText(finalcart.this,"Add items to your cart",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            private void buttonclick2() {
                remove = (Button) findViewById(R.id.remove);
                remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (data!=null) {
                            deldata();

                            Intent in = new Intent(finalcart.this, finalcart.class);
                            startActivity(in);
                        }
                        else{
                            Toast.makeText(finalcart.this,"Select an item",Toast.LENGTH_SHORT).show();
                        }

                    }


                });
            }
    private void deldata() {
        loading = ProgressDialog.show(this, "Deleting", "please wait...", false, false);

        String url = Config.DEL_URL;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                Toast.makeText(finalcart.this, response, Toast.LENGTH_SHORT).show();
                data=null;

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(finalcart.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }

                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put(Config.KEY_NAMEUSER, login.name);
                params.put(Config.KEY_PHONE3, login.phone);
                params.put(Config.KEY_ID,data);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
        }


