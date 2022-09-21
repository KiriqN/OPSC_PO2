package com.example.opsc_poe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class LandmarkItemPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landmark_item_page);

        String landmark_name = getIntent().getStringExtra("landmark_name");
        //int lat = getIntent().getIntExtra("lat");
        //int log = getIntent().getIntExtra("log");

        TextView name = findViewById(R.id.landmark_text_name);

        name.setText(landmark_name);

    }
}