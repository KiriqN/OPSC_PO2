package com.example.opsc_poe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opsc_poe.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {




    FirebaseDatabase database = FirebaseDatabase.getInstance("https://opsc-poe2-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference myRef = database.getReference("users");



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
                else if (!nameTxt.isEmpty() || !passwordTxt.isEmpty()) {

                    database.getReference().child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.hasChild(nameTxt)){

                                final String getPassword = snapshot.child(nameTxt).child("password").getValue(String.class);

                                if(getPassword.equals(passwordTxt)){
                                    Toast.makeText(Login.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Login.this, MainActivity.class));
                                    finish();
                                }
                                else {
                                    Toast.makeText(Login.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

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