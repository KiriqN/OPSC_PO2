package com.example.opsc_poe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class LandmarksPage extends AppCompatActivity {


    ArrayList<landmark_model> landmarks = new ArrayList<>();

    ArrayList<landmark_model> landmarks_display = new ArrayList<>();

    landmark_adapter adapter;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landmarks_page);



        SetupLandmarks();

        ResetDisplay();




        ResetRV();


        Button sort_btn = findViewById(R.id.sort_cat_01);
        sort_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplySorting();
            }
        });


        Button reset_btn = findViewById(R.id.reset_filter_button);
        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResetDisplay();
            }
        });


        Button favorite_btn = findViewById(R.id.favorite_button);
        favorite_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplyFavorite();
            }
        });



    }


    void ResetRV () {

        recyclerView = findViewById(R.id.landmark_rv);


        adapter = new landmark_adapter(this, landmarks_display, this);


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    void ResetDisplay () {

        landmarks_display.clear();



        for (int i = 0; i < landmarks.size(); i++) {

            landmarks_display.add(landmarks.get(i));



        }

        ResetRV();

        Toast.makeText(this, "reset items " + landmarks_display.size() + landmarks.size(), Toast.LENGTH_SHORT).show();


    }



    void ApplySorting () {

        for (int i = 0; i < landmarks.size(); i++) {


            if (landmarks.get(i).categoty_name != "hiking") {
                adapter.notifyItemRemoved(i);
                landmarks_display.remove(i);

            }




        }

        Toast.makeText(this, "sorted items " + landmarks_display.size() + landmarks.size(), Toast.LENGTH_SHORT).show();



    }

    void ApplyFavorite () {

        for (int i = 0; i < landmarks.size(); i++) {

            switch (landmarks.get(i).favorite) {

                case "unfavorite":
                    adapter.notifyItemRemoved(i);
                    landmarks_display.remove(i);
                    break;


            }


        }

        Toast.makeText(this, "sorted items " + landmarks_display.size() + landmarks.size(), Toast.LENGTH_SHORT).show();



    }

    private void SetupLandmarks () {

        landmarks.add(new landmark_model("Table Mountain", "hiking", "unfavorite"));
        landmarks.add(new landmark_model("Lion Hill", "sight seeing", "favorite"));

    }

    public void LoadItem (landmark_model item) {

        Intent intent = new Intent(LandmarksPage.this, LandmarkItemPage.class);

        intent.putExtra("landmark_name", item.landmark_name);

        startActivity(intent);

    }


}