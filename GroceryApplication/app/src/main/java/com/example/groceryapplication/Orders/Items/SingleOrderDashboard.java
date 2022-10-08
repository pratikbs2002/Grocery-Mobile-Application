package com.example.groceryapplication.Orders.Items;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.groceryapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SingleOrderDashboard extends AppCompatActivity {

    int count = 0;
    String he;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    ArrayList<ItemListModel> itemListModelArrayList;
    ItemListAdapter itemListAdapter;
    RecyclerView recyclerView;
    String fid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_order_dashboard);

        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.history_cycle_single_order_dashboard);
        CardView AddNewThingButton = (CardView) findViewById(R.id.AddNewThingButton_single_order_dashboard);

        fid = getIntent().getStringExtra("fid");
        firebaseAuth = FirebaseAuth.getInstance();
        itemListModelArrayList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        loaddata();
        String count2 = String.valueOf(count);
        AddNewThingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleOrderDashboard.this, AddSingleItem.class);
                intent.putExtra("fid", fid);
                startActivity(intent);
            }
        });

    }

    private void loaddata() {
        firebaseFirestore.collection("Orders").document(firebaseAuth.getUid()).collection("UserOrders").document(fid).collection("Items")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        transactionModelArrayList.clear();
                        for (DocumentSnapshot ds : task.getResult()) {
                            ItemListModel model = new ItemListModel(ds.getString("Item Name"), ds.getString("Item Weight"));
                            itemListModelArrayList.add(model);
                            count++;
                        }
                        count++;
                        he = String.valueOf(count);

                        itemListAdapter = new ItemListAdapter(SingleOrderDashboard.this, itemListModelArrayList);
                        recyclerView.setAdapter(itemListAdapter);
                    }
                });
    }
}