package com.mohitvirmani.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FullTest extends AppCompatActivity implements Phy_Adapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private ImageButton back;
    private List<Phy> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_test);


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_me);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        back = (ImageButton) findViewById(R.id.back);

        list = new ArrayList<>();
        list.add(new Phy("Mock Test 1"));
        list.add(new Phy("Mock Test 2"));
        list.add(new Phy("Mock Test 3"));
        list.add(new Phy("Mock Test 4"));
        list.add(new Phy("Mock Test 5"));
        list.add(new Phy("Mock Test 6"));
        list.add(new Phy("Mock Test 7"));
        list.add(new Phy("Mock Test 8"));


        Phy_Adapter mAdapter2 = new Phy_Adapter(FullTest.this, list);
//        mAdapter = new Phy_Adapter(Main29Activity.this,mUploads);

        mAdapter2.setOnItemClickListener(FullTest.this);
        mRecyclerView.setAdapter(mAdapter2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FullTest.this, MainPage.class));
            }
        });
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("fulltest").child(firebaseUser.getUid());
        databaseReference.removeValue();


    }

    @Override
    public void itemClicked(int position) {

        Phy myList = list.get(position);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("chapter", myList.getChapters());
        editor.apply();


        Intent intent = new Intent(this, FullTestInstr.class);
        startActivity(intent);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainPage.class));
    }
}