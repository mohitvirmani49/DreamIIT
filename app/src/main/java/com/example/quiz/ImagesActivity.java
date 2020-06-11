package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ImagesActivity extends AppCompatActivity implements ImageAdapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private Button floatingActionButton;
    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;
    private LottieAnimationView animationView;
    private SearchView search;
    private ImageButton back;
    private ImageButton filter;
    private RelativeLayout myTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        myTextView = (RelativeLayout) findViewById(R.id.textV);
        search = (androidx.appcompat.widget.SearchView) findViewById(R.id.search);
        back = (ImageButton) findViewById(R.id.back);
        filter = (ImageButton) findViewById(R.id.filter);
        mRecyclerView = findViewById(R.id.recycler_view);
        animationView = (LottieAnimationView) findViewById(R.id.my_progress);
        floatingActionButton = (Button) findViewById(R.id.floatwithme);

        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImagesActivity.this, Main16Activity.class);
                startActivity(intent);
            }
        });

        mUploads = new ArrayList<>();
//        mdispNames = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
//                    DispName dispName = postSnapshot.getValue(DispName.class);
                    mUploads.add(upload);
//                    mdispNames.add(dispName);

                }
                mAdapter = new ImageAdapter(ImagesActivity.this, mUploads);
                mAdapter.setOnItemClickListener(ImagesActivity.this);


//                nAdapter = new NameAdapter(ImagesActivity.this, mdispNames);
                mRecyclerView.setAdapter(mAdapter);
//                mRecyclerView.setAdapter(nAdapter);
                animationView.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ImagesActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                animationView.setVisibility(View.INVISIBLE);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImagesActivity.this, Main14Activity.class);
                startActivity(intent);
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImagesActivity.this, Main24Activity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void itemClicked(int position) {

        Upload clickedItem = mUploads.get(position);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("question", clickedItem.getmName());
        editor.putString("image_q", clickedItem.getmImageUrl());
        editor.putString("username", clickedItem.getmDisplayName());
        editor.putString("userpic", clickedItem.getmDisplayImage());
        editor.apply();

        Intent intent = new Intent(this, Main17Activity.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ImagesActivity.this, Main14Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(search != null){
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    searchMe(newText);
                    return true;
                }
            });
        }
    }
    private void searchMe(String str){
        ArrayList<Upload> myList = new ArrayList<>();

        for(Upload object : mUploads){
            if(object.getmName().toLowerCase().contains(str.toLowerCase())){
                myList.add(object);
            }

        }
        ImageAdapter mAdapter2 = new ImageAdapter(getApplicationContext(),myList);
        mRecyclerView.setAdapter(mAdapter2);
        mAdapter2.setOnItemClickListener(ImagesActivity.this);


    }

}