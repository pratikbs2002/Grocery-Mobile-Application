package com.example.groceryapplication.Orders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryapplication.Orders.Items.SingleOrderDashboard;
import com.example.groceryapplication.R;

import java.util.ArrayList;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
    Context context;
    ArrayList<OrderModel> orderModelArrayList;

    public OrderAdapter(Context context, ArrayList<OrderModel> orderModelArrayList) {
        this.context = context;
        this.orderModelArrayList = orderModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_order_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        OrderModel model = orderModelArrayList.get(position);
        holder.Order_id.setText(model.getOrder_id());
        holder.Order_date.setText(model.getOrder_date());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SingleOrderDashboard.class);
                intent.putExtra("fid", orderModelArrayList.get(holder.getAdapterPosition()).getOrder_fid());
                context.startActivity(intent);
//                ((Activity) context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Order_id, Order_date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Order_id = itemView.findViewById(R.id.OrderID_single);
            Order_date = itemView.findViewById(R.id.OrderDate_single);
        }
    }
}
