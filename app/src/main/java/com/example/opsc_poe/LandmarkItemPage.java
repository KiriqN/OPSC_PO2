package com.example.opsc_poe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LandmarkItemPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landmark_item_page);

        String landmark_name = getIntent().getStringExtra("landmark_name");
        int lat = getIntent().getIntExtra("lat", 0);
        int log = getIntent().getIntExtra("log", 0);

        Toast.makeText(this, "here is " + lat, Toast.LENGTH_SHORT).show();


        TextView name = findViewById(R.id.landmark_text_name);

        name.setText(landmark_name);

        Button btn = findViewById(R.id.open_landmark_map);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandmarkItemPage.this, Map.class);

                intent.putExtra("lat", lat);
                intent.putExtra("log", log);

                startActivity(intent);
            }
        });

    }
}