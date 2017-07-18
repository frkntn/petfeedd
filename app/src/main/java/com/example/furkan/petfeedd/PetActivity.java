package com.example.furkan.petfeedd;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class PetActivity extends AppCompatActivity {
    Button btnseviye,btnmama;
    TextView tvseviye;
    String json_url = "https://api.thingspeak.com/channels/272233/feed/last.json?api_key=A691IEYKHNDWIB22";


    public static double x;
    public double y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        btnseviye = (Button) findViewById(R.id.btn_seviye);
        tvseviye = (TextView) findViewById(R.id.tv_seviye);
        btnmama=(Button)findViewById(R.id.btn_mama);


        btnseviye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, json_url, (String) null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {

                                    y=response.getDouble("field2");
                                    if(y<=10){
                                        tvseviye.setText("0.00");

                                    }
                                    else {
                                        tvseviye.setText(response.getString("field2"));
                                        x = response.getDouble("field2");
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PetActivity.this, "Bir şeyler yanlış gitti", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();

                    }
                });

                MySingleton.getmInstance(PetActivity.this).addToRequestque(jsonObjectRequest);



            }
        });
        btnmama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r=new Intent(PetActivity.this,Mama.class);
                startActivity(r);
            }
        });
    }










}


