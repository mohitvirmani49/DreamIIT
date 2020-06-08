package com.example.quiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Upload> mUploads;
    private OnItemClickListener itemClickListener;
    //    OtemClickListener


    public interface OnItemClickListener {
        void itemClicked(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        itemClickListener = listener;

    }


    public ImageAdapter(Context context, List<Upload> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        Upload uploadCurrent = mUploads.get(position);
        System.out.println(":::::::::::" + uploadCurrent.getmImageUrl());

        if (!uploadCurrent.getmImageUrl().equals(null) && !uploadCurrent.getmImageUrl().equals("")) {


            holder.imageView.requestLayout();
            holder.imageView.getLayoutParams().height = 460;
            holder.imageView.getLayoutParams().width = 1050;


            holder.textView.setText(uploadCurrent.getmName());
            Picasso.get().load(uploadCurrent.getmImageUrl()).fit().centerCrop().into(holder.imageView);
            holder.textView1.setText(uploadCurrent.getmDisplayName());
            Picasso.get().load(uploadCurrent.getmDisplayImage()).fit().centerCrop().into(holder.im1);

        } else {
            holder.imageView.requestLayout();
            holder.imageView.getLayoutParams().height = 0;
            holder.imageView.getLayoutParams().width = 0;
//            holder.imageView.setImageBitmap(null);
//            holder.imageView.setVisibility(View.INVISIBLE);

            holder.textView.setText(uploadCurrent.getmName());
            holder.textView1.setText(uploadCurrent.getmDisplayName());
            Picasso.get().load(uploadCurrent.getmDisplayImage()).fit().centerCrop().into(holder.im1);


        }
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;
        public ImageView im1;
        public TextView textView1;


        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view_name);
            imageView = itemView.findViewById(R.id.image_view_upload);
            im1 = itemView.findViewById(R.id.display_image5);
            textView1 = itemView.findViewById(R.id.display_name5);
//            itemView.setOnClickListener(this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            itemClickListener.itemClicked(position);
                        }
                    }
                }
            });
        }
    }
}