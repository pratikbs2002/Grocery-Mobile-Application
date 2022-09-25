package com.example.admin;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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
        holder.Order_list.setText(model.getOrder_list());
        holder.Order_date.setText(model.getOrder_date());
    }

    @Override
    public int getItemCount() {
        return orderModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Order_id, Order_date, Order_list;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Order_list = itemView.findViewById(R.id.OrderText_single);
            Order_date = itemView.findViewById(R.id.DateOfOrder_single);
        }
    }
}
