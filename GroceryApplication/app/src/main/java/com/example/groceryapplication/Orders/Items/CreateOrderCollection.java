package com.example.groceryapplication.Orders.Items;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.groceryapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class CreateOrderCollection extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order_collection);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        CardView AddNewThingButton = findViewById(R.id.AddNewThingButton_create_order_collection);

        Intent intent = getIntent();
        String OrderID = intent.getStringExtra("he");


        AddNewThingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = UUID.randomUUID().toString();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy | h:mm:ss a", Locale.getDefault());
                String currentDate = simpleDateFormat.format(new Date());

                Map<String, Object> orders = new HashMap<>();

                orders.put("Order fid", id);
                orders.put("Order id", OrderID);
                orders.put("Order date", currentDate);

                firebaseFirestore.collection("Orders").document(firebaseAuth.getCurrentUser().getUid()).collection("UserOrders").document(id).set(orders).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Intent intent = new Intent(CreateOrderCollection.this, AddSingleItem.class);
                        intent.putExtra("fid", id);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CreateOrderCollection.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}