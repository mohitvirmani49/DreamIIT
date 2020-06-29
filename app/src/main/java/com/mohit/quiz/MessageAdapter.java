package com.mohit.quiz;

import android.content.Context;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ImageViewHolder> {
    public static final int MSG_TYPE_LEFT = 1;
    public static final int MSG_TYPE_RIGHT = 0;

    private Context mContext;
    private List<Comm> mUploads;
    private NotificationManagerCompat notificationManager;

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
//            notificationManager = NotificationManagerCompat.from(getApplicationContext());
//
//            SharedPreferences result = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                Notification notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_1_ID)
//                        .setSmallIcon(R.drawable.mohitpic)
//                        .setContentTitle("New Comment")
//                        .setContentText(result.getString("comm", "0"))
//                        .setPriority(NotificationCompat.PRIORITY_HIGH)
//                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
//                        .setAutoCancel(true)
//                        .setOnlyAlertOnce(true)
//                        .build();
//
//                notificationManager.notify(1, notification);

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


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("comm", uploadCurrent.getmComment());
        editor.putString("name789", uploadCurrent.getmCommentName());
        editor.apply();


        holder.textView_msgsent.setText(uploadCurrent.getmComment());
        holder.textView1_rec.setText(uploadCurrent.getmCommentName());

        System.out.println("Check Truth::::" + uploadCurrent.getmComment());


    }


    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_msgsent;

        public TextView textView1_rec;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_msgsent = itemView.findViewById(R.id.msg_for);
            textView1_rec = itemView.findViewById(R.id.myName);
        }

    }


}