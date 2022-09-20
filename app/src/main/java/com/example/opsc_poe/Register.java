package com.example.opsc_poe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://opsc-poe2-default-rtdb.europe-west1.firebasedatabase.app/").getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText email = findViewById(R.id.email);

        final EditText username = findViewById(R.id.username);

        final EditText password = findViewById(R.id.password);

        final EditText conPassword = findViewById(R.id.conPassword);

        final AppCompatButton registerBtn = findViewById(R.id.registerBtn);

        final TextView signIn = findViewById(R.id.signIn);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                final String emailTxt = email.getText().toString();

                final String nameTxt = username.getText().toString();

                final String passwordTxt = password.getText().toString();

                final String conPasswordTxt = conPassword.getText().toString();

                if (emailTxt.isEmpty() || nameTxt.isEmpty() || passwordTxt.isEmpty() || conPasswordTxt.isEmpty())
                {
                    Toast.makeText(Register.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
                else if (!passwordTxt.equals(conPasswordTxt)){
                    Toast.makeText(Register.this, "Please make sure passwords are matching", Toast.LENGTH_SHORT).show();
                }

                else if(passwordTxt.equals(conPasswordTxt)) {

                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(emailTxt))
                            {
                                Toast.makeText(Register.this, "This email is already registered to a account", Toast.LENGTH_SHORT).show();
                            }

                            else {
                                databaseReference.child("user").child(emailTxt).child("username").setValue(nameTxt);
                                databaseReference.child("user").child(emailTxt).child("email").setValue(emailTxt);
                                databaseReference.child("user").child(emailTxt).child("password").setValue(passwordTxt);

                                Toast.makeText(Register.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });




                }
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}