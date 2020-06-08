package com.example.quiz;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ImageViewHolder> {
    public static final int MSG_TYPE_LEFT = 1;
    public static final int MSG_TYPE_RIGHT = 0;

    private Context mContext;
    private List<Comm> mUploads;



    public MessageAdapter(Context context, List<Comm> doubts) {
        mContext = context;
        mUploads = doubts;
    }
    @Override
    public int getItemViewType(int position) {
        FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        if(mUploads.get(position).getmCommentName().equals(user.getDisplayName())){
            System.out.println("Aaalaaa re aalaaaa" + user.getUid());
            return MSG_TYPE_RIGHT;
        }else {
            System.out.println("HAAHAHAHA");
            return MSG_TYPE_LEFT;

        }
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_TYPE_RIGHT) {
            System.out.println(":::::::::::::mnmn" + MSG_TYPE_RIGHT);

            View v = LayoutInflater.from(mContext).inflate(R.layout.message_send, parent, false);
            return new ImageViewHolder(v);
        }else {
            View v = LayoutInflater.from(mContext).inflate(R.layout.message_recieve, parent, false);
            return new ImageViewHolder(v);

        }


    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        Comm uploadCurrent = mUploads.get(position);


        holder.textView_msgsent.setText(uploadCurrent.getmComment());
        holder.textView1_rec.setText(uploadCurrent.getmCommentName());
//
//        holder.tv2.setText(uploadCurrent.getmComment());
//        holder.tv3.setText(uploadCurrent.getmCommentName());

        System.out.println("Check Truth::::" + uploadCurrent.getmComment());


    }


    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_msgsent;
//        public ImageView imageView_sent;
//        public ImageView im1_rec;
        public TextView textView1_rec;
//        public TextView tv2;
//        public TextView tv3;



        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_msgsent = itemView.findViewById(R.id.msg_for);
            textView1_rec = itemView.findViewById(R.id.myName);
//            tv2 = itemView.findViewById(R.id.msg_for5);
//            tv3 = itemView.findViewById(R.id.myName5);
//            im1_rec = itemView.findViewById(R.id.msg_sent);
//            textView1_rec = itemView.findViewById(R.id.show_msg);
//            itemView.setOnClickListener(this);

        }

    }


}