package com.mohitvirmani.quiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotifyAdapter extends RecyclerView.Adapter<NotifyAdapter.ItemViewHolder> {
    private List<Phy> myList;
    private Phy_Adapter.OnItemClickListener itemClickListener;
    private Context mContext;

    public interface OnItemClickListener {
        void itemClicked(int position);

    }

    public void setOnItemClickListener(Phy_Adapter.OnItemClickListener listener) {
        itemClickListener = listener;

    }

    NotifyAdapter(Context context, List<Phy> list) {
        mContext = context;
        myList = list;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println(myList.size() + " Hii Let me check you");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.noti_recycler, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Phy upload = myList.get(position);
        holder.textView.setText(upload.getChapters());
        if (position == 0) {
            holder.container.setBackgroundResource(R.drawable.round_corner);
            holder.setIsRecyclable(false);
        }
        if (position == myList.size() - 1) {
            holder.container.setBackgroundResource(R.drawable.round_corner_btm);
            holder.divider.setVisibility(View.GONE);
            holder.setIsRecyclable(false);
        }

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout container;
        View divider;
        TextView textView;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            divider = itemView.findViewById(R.id.divider);
            textView = itemView.findViewById(R.id.tv3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            itemClickListener.itemClicked(position);
                            System.out.println("My Game, My Rule::::::" + position);
                        }
                    }
                }
            });

        }
    }
}