package com.example.admin.Items;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.R;

import java.util.ArrayList;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.MyViewHolder> {
    Context context;
    ArrayList<ItemListModel> itemModelArrayList;

    public ItemListAdapter(Context context, ArrayList<ItemListModel> itemModelArrayList) {
        this.context = context;
        this.itemModelArrayList = itemModelArrayList;
    }


    @NonNull
    @Override
    public ItemListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_view, parent, false);
        return new ItemListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemListModel model = itemModelArrayList.get(position);
        holder.Item_Name.setText(model.getItem_Name());
        holder.Item_weight.setText(model.getItem_Weight());

    }

    @Override
    public int getItemCount() {
        return itemModelArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Item_Name, Item_weight;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Item_Name = itemView.findViewById(R.id.ThingName_single);
            Item_weight = itemView.findViewById(R.id.ThingWeight_single);
        }
    }
}
