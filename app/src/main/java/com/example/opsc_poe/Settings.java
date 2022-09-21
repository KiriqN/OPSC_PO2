package com.example.opsc_poe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Settings extends AppCompatActivity {


    FirebaseDatabase database = FirebaseDatabase.getInstance("https://opsc-poe2-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference myRef = database.getReference("users");


     EditText setting_measurement;

     EditText setting_landmark;

     String setting_measurement_value;
    String setting_landmark_value;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



        String user = GlobalInfo.username;

        String land = GlobalInfo.user_settings_info.preferred_landmark;

        String mes = GlobalInfo.user_settings_info.measurement;


        Toast.makeText(this, user, Toast.LENGTH_SHORT).show();

        setting_measurement = findViewById(R.id.setting_id);

        setting_landmark = findViewById(R.id.landmark_type_setting);

        Button save_btn = findViewById(R.id.save_setting_button);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Save(setting_measurement.getText().toString(), setting_landmark.getText().toString());


            }
        });

        ApplySetting();


    }

    void ApplySetting () {


        setting_measurement.setText(GlobalInfo.user_settings_info.measurement);

        setting_landmark.setText(GlobalInfo.user_settings_info.preferred_landmark);




    }


    void Save (String measurement, String landmark_type) {

        GlobalInfo.user_settings_info = new SettingsType(measurement, landmark_type);

        startActivity(new Intent(Settings.this, MainActivity.class));

    }
}