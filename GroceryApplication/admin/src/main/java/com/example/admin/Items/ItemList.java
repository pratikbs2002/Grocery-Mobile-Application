package com.example.admin.Items;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.admin.Customers.Dashboard;
import com.example.admin.Orders.OrderList;
import com.example.admin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ItemList extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    ArrayList<ItemListModel> itemListModelArrayList;
    ItemListAdapter itemListAdapter;
    RecyclerView recyclerView;
    String fid, C_id;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        progressBar = findViewById(R.id.progressbar_item);
        progressBar.setVisibility(View.VISIBLE);


        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Items");

        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.history_cycle_single_order_itemList);

        fid = getIntent().getStringExtra("fid");
        C_id = getIntent().getStringExtra("C_id");
        firebaseAuth = FirebaseAuth.getInstance();
        itemListModelArrayList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        loaddata();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loaddata() {
        firebaseFirestore.collection("Orders").document(C_id).collection("UserOrders").document(fid).collection("Items").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        transactionModelArrayList.clear();
                for (DocumentSnapshot ds : task.getResult()) {
                    ItemListModel model = new ItemListModel(ds.getString("Item Name"), ds.getString("Item Weight"));
                    itemListModelArrayList.add(model);
                }

                itemListAdapter = new ItemListAdapter(ItemList.this, itemListModelArrayList);
                recyclerView.setAdapter(itemListAdapter);
            }
        });
        progressBar.setVisibility(View.GONE);
    }
}