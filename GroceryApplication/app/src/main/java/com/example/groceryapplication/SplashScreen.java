package com.example.groceryapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Space;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mAuth = FirebaseAuth.getInstance();

        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(2000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {

                    mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
                        @Override
                        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                            if (mAuth.getCurrentUser() != null) {
                                try {
                                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                                    finish();

                                } catch (Exception e) {

                                }
                            }
                            else {
                                Intent intent = new Intent(SplashScreen.this,LoginPage.class);
                                startActivity(intent);
                            }
                        }
                    });

                    finish();
                }
            }
        };thread.start();
    }
}