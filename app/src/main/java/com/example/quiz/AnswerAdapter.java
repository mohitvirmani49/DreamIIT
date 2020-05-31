package com.example.quiz;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Ans_Upload> mUploads;
//    private OnItemClickListener itemClickListener;
    //    OtemClickListener


    public AnswerAdapter(Context context, List<Ans_Upload> uploadsAns) {
        mContext = context;
        mUploads = uploadsAns;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.answer_view, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        Ans_Upload uploadCurrent = mUploads.get(position);
//        if (uploadCurrent.getmImageUrl().trim().isEmpty()) {
//            holder.imageButton.requestLayout();
//            holder.imageButton.getLayoutParams().height = 0;
//            holder.imageButton.getLayoutParams().width = 0;
////            holder.imageView.setImageBitmap(null);
////            holder.imageView.setVisibility(View.INVISIBLE);
//
        holder.textView.setText(uploadCurrent.getmName());
        holder.textView1.setText(uploadCurrent.getmDisplayName());
        Picasso.get().load(uploadCurrent.getmDisplayImage()).fit().centerCrop().into(holder.circularImageView);
//
//
//        } else {
//        holder.textView.setText(uploadCurrent.getmName());
//        Picasso.get().load(uploadCurrent.getmImageUrl()).fit().centerCrop().into(holder.imageButton);
//        holder.textView1.setText(uploadCurrent.getmDisplayName());
//        Picasso.get().load(uploadCurrent.getmDisplayImage()).fit().centerCrop().into(holder.circularImageView);
//
//        holder.imageButton.requestLayout();
//        holder.imageButton.getLayoutParams().height = 0;
//        holder.imageButton.getLayoutParams().width = 0;


//        }
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public TextView textView1;
        public ImageButton imageButton;
        public CircularImageView circularImageView;


        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.answer_text5);
            circularImageView = itemView.findViewById(R.id.answer_person_image);
            imageButton = itemView.findViewById(R.id.ans_image5);
            textView1 = itemView.findViewById(R.id.answer_user_name);
//            itemView.setOnClickListener(this);

        }
    }
}