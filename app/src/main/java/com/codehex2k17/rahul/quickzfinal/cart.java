package com.codehex2k17.rahul.quickzfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class cart extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextId;
    private Button buttonGet;
    Button btnauto;
    private TextView textViewResult;
    public static String Name;
    public static String Discount;
    double price;
    double discount;
    double finalprice;
    public static String Finalprice;
    public static String Price;

    public static String id;

    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        editTextId = (EditText) findViewById(R.id.editTextId1);
        buttonGet = (Button) findViewById(R.id.buttonGet1);
        textViewResult = (TextView) findViewById(R.id.textviewresult);

        buttonGet.setOnClickListener(this);

        Intent i = getIntent();
        id = i.getStringExtra("TextBox");

        editTextId.setText(id);
        buttonGet.performClick();
    }

    private void getData() {
        String id = editTextId.getText().toString().trim();
        if (id.equals("")) {
            Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
            return;
        }
        loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);

        String url = Config.DATA_URL + editTextId.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(cart.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        Toast.makeText(cart.this, "Not in the databases", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void showJSON(String response) {


        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject Data = result.getJSONObject(0);
            Name = Data.getString(Config.KEY_NAME);
            Price = Data.getString(Config.KEY_PRICE);
            Discount=Data.getString(Config.KEY_DISCOUNT);
            price=Double.parseDouble(Price);
            discount=Double.parseDouble(Discount);
            finalprice=price-(discount/100*price);
            Finalprice=Double.toString(finalprice);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        textViewResult.setText("Name:\t" + Name + "\nMRP:\tRs" + Price+ "\nDiscount:\t"+ discount + "%" + "\nFinal Price:\t" + Finalprice);
    }



    @Override
    public void onClick(View v) {
        if (v == buttonGet)
            getData();
        buttonclick();
    }



    private void buttonclick() {
        btnauto = (Button) findViewById(R.id.btnauto1);
        btnauto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1 = new Intent(cart.this, addcart.class);
                startActivity(in1);
            }

        });

    }
}