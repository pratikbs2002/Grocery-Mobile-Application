package com.example.groceryapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class AddNewOrder extends AppCompatActivity {

    FirebaseFirestore fStore;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    EditText OrderList;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_order);

        fStore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        Button btn = findViewById(R.id.SaveMyOrder_AddNewOrder);
        OrderList = findViewById(R.id.OrderList_AddNewOrder);
        Intent intent = getIntent();
        String OrderID =  intent.getStringExtra("count");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = UUID.randomUUID().toString();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy | h:mm:ss a", Locale.getDefault());
                String currentDate = simpleDateFormat.format(new Date());
                Map<String, Object> orders = new HashMap<>();

                orders.put("Order id", OrderID);
                orders.put("Order list", OrderList.getText().toString());
                orders.put("Order date", currentDate);
                fStore.collection("Orders").document(firebaseAuth.getCurrentUser().getUid()).collection("UserOrders").document(id)
                        .set(orders)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(AddNewOrder.this, currentDate, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(AddNewOrder.this, firebaseAuth.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(AddNewOrder.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });


    }
}