package com.example.groceryapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.groceryapplication.Orders.Dashboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {
    EditText LoginEmail, LoginPassword;
    ProgressBar ProgressBar_login;
    Button LoginButton;
    private FirebaseAuth mAuth;
    TextView RegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
//        getSupportActionBar().hide();


        LoginButton = findViewById(R.id.LoginButton);
        LoginEmail = findViewById(R.id.LoginEmail);
        LoginPassword = findViewById(R.id.LoginPassword);
        ProgressBar_login = findViewById(R.id.progressbar_login);
        RegisterButton = findViewById(R.id.Register_loginPage);

        mAuth = FirebaseAuth.getInstance();


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressBar_login.setVisibility(View.VISIBLE);
                userLogin();
            }
        });

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this, RegisterPage.class));
            }
        });

    }

    private void userLogin() {

        String email = LoginEmail.getText().toString().trim();
        String password = LoginPassword.getText().toString().trim();

        if (email.isEmpty()) {
            LoginEmail.setError("Email is required !");
            LoginEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            LoginPassword.setError("Password is required !");
            LoginPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(LoginPage.this, Dashboard.class));
//                    finish();
                } else {
                }
                ProgressBar_login.setVisibility(View.GONE);

            }
        });
    }
}