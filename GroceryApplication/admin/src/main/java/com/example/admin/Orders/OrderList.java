package com.example.admin.Orders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.admin.Customers.CustomerModel;
import com.example.admin.Customers.Dashboard;
import com.example.admin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class OrderList extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    ArrayList<OrderListModel> orderListModelArrayList;
    OrderListAdapter orderListAdapter;
    RecyclerView recyclerView;
    String C_id;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Orders");

        C_id = getIntent().getStringExtra("c_id");
        firebaseFirestore = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.history_cycle_orderAdmin);
        orderListModelArrayList = new ArrayList<>();

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
        firebaseFirestore.collection("Orders").document(C_id).collection("UserOrders").orderBy("Order date", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot ds : task.getResult()) {
                    OrderListModel model = new OrderListModel(ds.getId(), ds.getString("Order id"), ds.getString("Order date"), C_id);
                    orderListModelArrayList.add(model);
                }
                orderListAdapter = new OrderListAdapter(OrderList.this, orderListModelArrayList);
                recyclerView.setAdapter(orderListAdapter);
            }
        });
        progressBar.setVisibility(View.GONE);
    }
}

