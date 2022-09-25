package com.example.admin;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {
    Context context;
    ArrayList<CustomerModel> customerModelArrayList;
    TextView button;

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
        holder.CustomerName.setText(model.getCustomerName());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, update_transaction.class);
//                intent.putExtra("id", customerModelArrayList.get(holder.getAdapterPosition()).getId());
//                intent.putExtra("amount", customerModelArrayList.get(holder.getAdapterPosition()).getAmount());
//                intent.putExtra("note", customerModelArrayList.get(holder.getAdapterPosition()).getNote());
//                intent.putExtra("type", customerModelArrayList.get(holder.getAdapterPosition()).getType());
//                context.startActivity(intent);
//                ((Activity) context).finish();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return customerModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView CustomerName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            CustomerName = itemView.findViewById(R.id.CustomerName_admin);
        }
    }
}
