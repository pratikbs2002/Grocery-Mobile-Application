package com.example.admin.Orders;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.Items.ItemList;
import com.example.admin.R;

import java.util.ArrayList;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyViewHolder> {
    Context context;
    ArrayList<OrderListModel> orderListModelArrayList;

    public OrderListAdapter(Context context, ArrayList<OrderListModel> orderListModelArrayList) {
        this.context = context;
        this.orderListModelArrayList = orderListModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_order_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        OrderListModel model = orderListModelArrayList.get(position);
        holder.Order_id.setText(model.getOrder_id());
        holder.Order_date.setText(model.getOrder_date());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemList.class);
                intent.putExtra("fid", orderListModelArrayList.get(holder.getAdapterPosition()).getOrder_fid());
                intent.putExtra("C_id", orderListModelArrayList.get(holder.getAdapterPosition()).getC_id());
                context.startActivity(intent);
//                ((Activity) context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderListModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Order_id, Order_date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Order_id = itemView.findViewById(R.id.OrderID_single_admin);
            Order_date = itemView.findViewById(R.id.OrderDate_single_admin);
        }
    }
}
