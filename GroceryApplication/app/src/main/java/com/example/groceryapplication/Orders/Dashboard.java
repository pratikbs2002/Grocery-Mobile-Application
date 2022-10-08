package com.example.groceryapplication.Orders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.groceryapplication.Orders.Items.CreateOrderCollection;
import com.example.groceryapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    int count = 0;
    String he;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    ArrayList<OrderModel> orderModelArrayList;
    OrderAdapter orderAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.history_cycle);
        ImageView LogOut = findViewById(R.id.ProfileButton_Dashboard);

        CardView AddNewOrderButton = (CardView) findViewById(R.id.AddNewOrderButton);

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogOut();
            }
        });

        orderModelArrayList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        loaddata();

        String count2 = String.valueOf(count);
        AddNewOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Dashboard.this, CreateOrderCollection.class);
                intent.putExtra("he", he);
                startActivity(intent);

            }
        });

    }

    private void LogOut() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
        builder.setTitle("LOG OUT").setMessage("Are you sure you want to Log out ?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Dashboard.this, Dashboard.class));
                finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    private void loaddata() {
        firebaseFirestore.collection("Orders").document(firebaseAuth.getUid()).collection("UserOrders").orderBy("Order date", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        transactionModelArrayList.clear();
                for (DocumentSnapshot ds : task.getResult()) {
                    OrderModel model = new OrderModel(ds.getId(), ds.getString("Order id"), ds.getString("Order date"));
                    orderModelArrayList.add(model);
                    count++;
                }
                count++;
                he = String.valueOf(count);


                orderAdapter = new OrderAdapter(Dashboard.this, orderModelArrayList);
                recyclerView.setAdapter(orderAdapter);
            }
        });
    }
}