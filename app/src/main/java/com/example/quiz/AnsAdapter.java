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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import ozaydin.serkan.com.image_zoom_view.ImageViewZoom;

import static com.facebook.FacebookSdk.getApplicationContext;

public class AnsAdapter extends RecyclerView.Adapter<AnsAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Ans_Upload> uploadsm;
    private OnItemClickListener itemClickListener;
    //    OtemClickListener


    public interface OnItemClickListener {
        void itemClicked(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        itemClickListener = listener;

    }


    public AnsAdapter(Context context, List<Ans_Upload> uploads) {
        mContext = context;
        uploadsm = uploads;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.answer_view, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolder holder, final int position) {

        final Ans_Upload uploadCurrent = uploadsm.get(position);
        System.out.println(":::::::::::" + uploadCurrent.getmImageUrl());

        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    Intent intent = new Intent(getApplicationContext(), Main21Activity.class);

                    Ans_Upload clickedItem = uploadsm.get(position);
                    intent.putExtra("correct", clickedItem.getmName());
                    mContext.startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Can't comment on this question", Toast.LENGTH_SHORT).show();
                }
            }
        });


        holder.like.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                int love = 0;
                FirebaseAuth firebaseAuth;
                firebaseAuth = FirebaseAuth.getInstance();
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                love = love + 1;
                final Like like = new Like(String.valueOf(love));
                DatabaseReference myref = FirebaseDatabase.getInstance().getReference("likes");
                Ans_Upload my = uploadsm.get(position);
                myref.child(my.getmName()).child(user.getUid()).setValue(like);


                Query query = FirebaseDatabase.getInstance().getReference("likes").child(my.getmName());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int total = (int) dataSnapshot.getChildrenCount();
                        System.out.println("My val, plz tell" + total);
                        holder.numberlikes.setText(String.valueOf(total));


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                FirebaseAuth firebaseAuth;
                firebaseAuth = FirebaseAuth.getInstance();
                final FirebaseUser user = firebaseAuth.getCurrentUser();

                Ans_Upload my = uploadsm.get(position);

                DatabaseReference myref = FirebaseDatabase.getInstance().getReference("likes").child(my.getmName()).child(user.getUid());


                myref.removeValue();
                Query query = FirebaseDatabase.getInstance().getReference("likes").child(my.getmName()).child(user.getUid());
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        int total = (int) dataSnapshot.getChildrenCount();
                        System.out.println("My val, plz tell" + total);
                        holder.numberlikes.setText(String.valueOf(total));


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


        if (!uploadCurrent.getmImageUrl().equals(null) && !uploadCurrent.getmImageUrl().equals("")) {


            holder.imageView.requestLayout();
            holder.imageView.getLayoutParams().height = 460;
            holder.imageView.getLayoutParams().width = 1050;


            holder.textView.setText(uploadCurrent.getmName());

            Picasso.get().load(uploadCurrent.getmImageUrl()).fit().centerCrop().into(holder.imageView);
//            holder.textView1.setText(uploadCurrent.getmDisplayName());
            holder.tv2.setText(uploadCurrent.getmDisplayName());
//            Picasso.get().load(uploadCurrent.getmDisplayImage()).fit().centerCrop().into(holder.im1);

        } else {
            holder.imageView.requestLayout();
            holder.imageView.getLayoutParams().height = 0;
            holder.imageView.getLayoutParams().width = 0;
//            holder.imageView.setImageBitmap(null);
//            holder.imageView.setVisibility(View.INVISIBLE);

            holder.textView.setText(uploadCurrent.getmName());
            holder.tv2.setText(uploadCurrent.getmDisplayName());


        }


    }

    @Override
    public int getItemCount() {
        return uploadsm.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageViewZoom imageView;
        public TextView comment;
        public TextView tv2;
        public LikeButton like;
        public TextView numberlikes;


        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.answer_text5);
            imageView = itemView.findViewById(R.id.ans_image5);
            comment = itemView.findViewById(R.id.comments);
            tv2 = itemView.findViewById(R.id.answer_user_name);
            like = itemView.findViewById(R.id.like);
            numberlikes = itemView.findViewById(R.id.no_of_likes);
//            rank = itemView.findViewById(R.id.myvalue);
//            itemView.setOnClickListener(this);
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