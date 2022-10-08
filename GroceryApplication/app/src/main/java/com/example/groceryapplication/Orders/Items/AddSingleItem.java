package com.example.groceryapplication.Orders.Items;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.groceryapplication.Orders.Dashboard;
import com.example.groceryapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddSingleItem extends AppCompatActivity {

    FirebaseFirestore fStore;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    EditText ItemName, ItemWeight;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_single_item);

        fStore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        Button btn = findViewById(R.id.AddItem_AddNewItem);

        Intent intent = getIntent();
        String fid = intent.getStringExtra("fid");

        ItemName = findViewById(R.id.ItemName_AddNewItem);
        ItemWeight = findViewById(R.id.ItemWeight_AddNewItem);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String Item_Name = ItemName.getText().toString().trim();
                String Item_Weight = ItemWeight.getText().toString().trim();

                if (Item_Name.isEmpty()) {
                    ItemName.setError("Item Name is required !");
                    ItemName.requestFocus();
                    return;
                }

                if (Item_Weight.isEmpty()) {
                    ItemWeight.setError("Item Weight is required !");
                    ItemWeight.requestFocus();
                    return;
                }


                String newId = UUID.randomUUID().toString();

                Map<String, Object> orders = new HashMap<>();

                orders.put("Item Name", Item_Name);
                orders.put("Item Weight", Item_Weight);

                fStore.collection("Orders").document(firebaseAuth.getCurrentUser().getUid()).collection("UserOrders").document(fid).collection("Items").document(newId)
                        .set(orders)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Intent intent = new Intent(AddSingleItem.this, SingleOrderDashboard.class);
                                intent.putExtra("fid", fid);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddSingleItem.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    public void onBackPressed() {

        this.startActivity(new Intent(AddSingleItem.this, Dashboard.class));
        finish();


    }

}
