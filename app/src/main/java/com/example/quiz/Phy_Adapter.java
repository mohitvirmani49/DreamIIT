package com.example.quiz;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Phy_Adapter extends RecyclerView.Adapter<Phy_Adapter.ItemViewHolder> {
    private List<Phy> myList;

    Phy_Adapter(List<Phy> list){
        myList = list;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.phy_recycler,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Phy upload = myList.get(position);
        holder.textView.setText(upload.getChapters());
        if(position == 0){
            holder.container.setBackgroundResource(R.drawable.round_corner);
            holder.setIsRecyclable(false);
        }
        if(position == myList.size() - 1){
            holder.container.setBackgroundResource(R.drawable.round_corner_btm);
            holder.divider.setVisibility(View.GONE);
            holder.setIsRecyclable(false);
        }

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout container;
        View divider;
        TextView textView;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            divider = itemView.findViewById(R.id.divider);
            textView = itemView.findViewById(R.id.tv3);

        }
    }
}
