package com.example.groceryapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPage extends AppCompatActivity {
    Button bt;

    private EditText editTextName, editTextEmail, editTextPassword, editTextMobile;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();


        editTextName = (EditText) findViewById(R.id.Register_UserName);
        editTextEmail = (EditText) findViewById(R.id.Register_UserEmail);
        editTextPassword = (EditText) findViewById(R.id.Register_Password);
        editTextMobile = (EditText) findViewById(R.id.Register_Mobile);
        bt = findViewById(R.id.Register_registerBtn);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUser();
            }
        });
    }

    private void RegisterUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String phone = editTextMobile.getText().toString().trim();

        if (name.isEmpty()) {
            editTextName.setError("FULL NAME IS REQUIRED");
            editTextName.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            editTextMobile.setError("MOBILE IS REQUIRED");
            editTextMobile.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editTextEmail.setError("EMAIL IS REQUIRED");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("please provide valid email");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("PASSWORD IS REQUIRED");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Minimum length of password is 6");
            editTextPassword.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    User user1 = new User(name, email, phone);
                    FirebaseDatabase.getInstance().getReference("users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterPage.this, "Registration s", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(RegisterPage.this, "Registration failed", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(RegisterPage.this, "Regiation s", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}