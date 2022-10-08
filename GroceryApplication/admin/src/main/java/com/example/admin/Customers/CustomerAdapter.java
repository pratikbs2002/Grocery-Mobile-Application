package com.example.admin.Customers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.Orders.OrderList;
import com.example.admin.R;

import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {
    Context context;
    ArrayList<CustomerModel> customerModelArrayList;

    public CustomerAdapter(Context context, ArrayList<CustomerModel> customerModelArrayList) {
        this.context = context;
        this.customerModelArrayList = customerModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_customer_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CustomerModel model = customerModelArrayList.get(position);
        holder.C_Name.setText(model.getC_Name());
        holder.C_Mobile.setText(model.getC_Mobile());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderList.class);
                intent.putExtra("c_id", customerModelArrayList.get(holder.getAdapterPosition()).getC_Id());
                context.startActivity(intent);
//                ((Activity) context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return customerModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView C_Name, C_Mobile;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            C_Name = itemView.findViewById(R.id.CustomerName_SingleView);
            C_Mobile = itemView.findViewById(R.id.MobileNumeber_SingleView);
        }
    }
}