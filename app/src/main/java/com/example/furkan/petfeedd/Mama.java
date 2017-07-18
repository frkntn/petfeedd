package com.example.furkan.petfeedd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import javax.security.auth.callback.Callback;

/**
 * Created by furkan on 21.05.2017.
 */

public class Mama extends AppCompatActivity {

    String c;
    Button gonder;
    Spinner spinner;
    String s = "A691IEYKHNDWIB22";//Thingspeak Key

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mama);

        gonder = (Button) findViewById(R.id.btn_gonder);
        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.mama_seviye, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (parent.getItemIdAtPosition(pos) == 1) {
                    c = "50";
                    if (PetActivity.x > 50) {
                        Toast.makeText(getBaseContext(), PetActivity.x + " " + "gram dolu", Toast.LENGTH_LONG).show();
                    }
                } else if (parent.getItemIdAtPosition(pos) == 2) {
                    c = "100";
                }
                //Toast.makeText(getBaseContext(),parent.getItemAtPosition(pos)+" Mama Eklemek İçin Doldur Butonuna Basınız...",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PetActivity.x <= 50) {


                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request.Builder builder = new Request.Builder();
                    Request request = builder.url("https://api.thingspeak.com/update?api_key=" + s + "&field1=" + c).build();
                    Toast.makeText(getBaseContext(), "Doldurma işlemi Başarılı", Toast.LENGTH_SHORT).show();

                    okHttpClient.newCall(request).enqueue(new com.squareup.okhttp.Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {

                        }

                        @Override
                        public void onResponse(Response response) throws IOException {



                        }
                    });

                } //if bitişi
                else {
                    Toast.makeText(getBaseContext(), "Kap yeteri kadar dolu..", Toast.LENGTH_LONG).show();
                }

            }

        });

    }


}