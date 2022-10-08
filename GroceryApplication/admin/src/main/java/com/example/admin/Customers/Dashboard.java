package com.example.admin.Customers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.admin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {


    FirebaseFirestore firebaseFirestore;
    ArrayList<CustomerModel> customerModelArrayList;
    CustomerAdapter customerAdapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        progressBar = findViewById(R.id.progressbar);
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.history_cycle_dashboard);

        customerModelArrayList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        progressBar.setVisibility(View.VISIBLE);
        loaddata();

    }


    private void loaddata() {
        firebaseFirestore.collection("Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot ds : task.getResult()) {
                    CustomerModel model = new CustomerModel(ds.getString("UserName"), ds.getString("MobileNumber"), ds.getId());
                    customerModelArrayList.add(model);
                    progressBar.setVisibility(View.GONE);
                }

                customerAdapter = new CustomerAdapter(Dashboard.this, customerModelArrayList);
                recyclerView.setAdapter(customerAdapter);
            }
        });
        progressBar.setVisibility(View.GONE);

    }
}
