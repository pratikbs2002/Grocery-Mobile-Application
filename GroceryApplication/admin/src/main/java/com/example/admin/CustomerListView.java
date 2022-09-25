package com.example.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CustomerListView extends AppCompatActivity {

    ArrayList<CustomerModel> customerModelArrayList;
    CustomerAdapter customerAdapter;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private DatabaseReference reference;
    RecyclerView ListViewOfCustomers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list_view);
        ListViewOfCustomers = findViewById(R.id.ListViewOfCustomers);
        customerModelArrayList = new ArrayList<>();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        loaddata();

    }

    private void loaddata() {
        firebaseFirestore.collection("Orders").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                customerAdapter = new CustomerAdapter(CustomerListView.this,customerModelArrayList);
                ListViewOfCustomers.setAdapter(customerAdapter);

            }
        });
    }



}