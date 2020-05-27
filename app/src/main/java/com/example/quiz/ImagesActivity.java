package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ImagesActivity extends AppCompatActivity implements ImageAdapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private Button floatingActionButton;
    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        mRecyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.pg);
        floatingActionButton = (Button) findViewById(R.id.floatwithme);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ImagesActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);

            }
        });
    }

    @Override
    public void itemClicked(int position) {
        Intent intent = new Intent(this, Main17Activity.class);
        startActivity(intent);

    }
}