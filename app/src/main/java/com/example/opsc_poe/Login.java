package com.example.opsc_poe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = findViewById(R.id.username);

        final EditText password = findViewById(R.id.password);

        final AppCompatButton login = findViewById(R.id.loginBtn);

        final TextView registerNow = findViewById(R.id.registerNow);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String nameTxt = username.getText().toString();

                final String passwordTxt = password.getText().toString();

                if (nameTxt.isEmpty() || passwordTxt.isEmpty())
                {
                    Toast.makeText(Login.this, "Please enter your username and password", Toast.LENGTH_SHORT).show();
                }
                else {

                }
            }
        });

        registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));


            }
        });
    }
}