package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Home extends AppCompatActivity {

    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();


    public String difficulty;
    public TextView dif;
    public  TextView sup;
    public  TextView master;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        StrictMode.setThreadPolicy(policy);

        dif = findViewById(R.id.testBox);
        sup = findViewById(R.id.supply);
        master = findViewById(R.id.master);


        dif.setText(getData("https://thegmtexplorer.com/api/getdifficulty"));
        sup.setText(getData("https://thegmtexplorer.com/ext/getmoneysupply"));
        master.setText(getData("https://thegmtexplorer.com/api/getblockcount"));


    }

    public  static  String getData(String url){

        StringBuilder content = new StringBuilder();
        // Use try and catch to avoid the exceptions
        try
        {
            URL Url = new URL(url); // creating a url object
            URLConnection urlConnection = Url.openConnection(); // creating a urlconnection object

            // wrapping the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // reading from the urlconnection using the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }

    public void refresh(View view) {

        dif.setText(getData("https://thegmtexplorer.com/api/getdifficulty"));
        sup.setText(getData("https://thegmtexplorer.com/ext/getmoneysupply"));
        master.setText(getData("https://thegmtexplorer.com/api/getblockcount"));


    }
}