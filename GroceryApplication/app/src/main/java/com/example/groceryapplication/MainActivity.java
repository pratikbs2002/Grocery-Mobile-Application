package com.example.groceryapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int count;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    ArrayList<OrderModel> orderModelArrayList;
    OrderAdapter orderAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.history_cycle);

        Button AddNewOrderButton = findViewById(R.id.AddNewOrderButton);
        AddNewOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 Intent intent = new Intent(MainActivity.this, AddNewOrder.class);
                 intent.putExtra("count",count);
                 startActivity(intent);

            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        orderModelArrayList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        loaddata();


    }

    private void loaddata() {
        firebaseFirestore.collection("Orders").document(firebaseAuth.getUid()).collection("UserOrders")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        transactionModelArrayList.clear();
                        count = 0;
                        for (DocumentSnapshot ds : task.getResult()) {
                            OrderModel model = new OrderModel(
                                    ds.getString("Order id"),
                                    ds.getString("Order date"),
                                    ds.getString("Order list"));

                            orderModelArrayList.add(model);
                            count++;
                        }
                        String he = String.valueOf(count);
                        Toast.makeText(MainActivity.this, he, Toast.LENGTH_SHORT).show();

                        orderAdapter = new OrderAdapter(MainActivity.this, orderModelArrayList);
                        recyclerView.setAdapter(orderAdapter);
                    }
                });
    }
}