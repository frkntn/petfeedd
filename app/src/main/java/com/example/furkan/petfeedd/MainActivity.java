package com.example.furkan.petfeedd;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText etId, etPass;
    Button btnlog;
    String userId,userPas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PetActivity pa=new PetActivity();

        etId = (EditText) findViewById(R.id.et_id);
        etPass = (EditText) findViewById(R.id.et_pas);
        btnlog = (Button) findViewById(R.id.btn_log);

        if(PetActivity.x <40) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
            builder.setSmallIcon(R.drawable.push_notifications1600);
            builder.setContentTitle("PetFeed Bildirim");
            builder.setContentText("Mama miktarı azalıyor!!!");
            Intent intent = new Intent(MainActivity.this, Mama.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);
            stackBuilder.addParentStack(Mama.class);
            stackBuilder.addNextIntent(intent);
            PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
            NotificationManager NM = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NM.notify(0, builder.build());
        }




    }


    public void onlogin(View view) {
        userId = etId.getText().toString().trim();
        userPas = etPass.getText().toString().trim();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, userId, userPas);

    }

    }
