package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main29Activity extends AppCompatActivity implements Phy_Adapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private ImageButton back;
    private List<Phy> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main29);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_me);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        back = (ImageButton) findViewById(R.id.back);
//        List<Phy> list = new ArrayList<>();
        list = new ArrayList<>();
        list.add(new Phy("\n1 Units and Measurement"));
        list.add(new Phy("\n2. Kinematics"));
        list.add(new Phy("\n3. Laws Of Motion"));
        list.add(new Phy("\n4. Work, Energy and Power"));
        list.add(new Phy("\n5. Rotational Motion"));
        list.add(new Phy("\n6. Gravitation"));
        list.add(new Phy("\n7. Properties Of Solids and Liquids"));
        list.add(new Phy("\n8. Thermodynamics"));
        list.add(new Phy("\n9. Kinetic Theory Of Gases"));
        list.add(new Phy("\n10. Oscillations and Waves"));
        list.add(new Phy("\n11. Electrostatics"));
        list.add(new Phy("\n12. Current Electricity"));
        list.add(new Phy("\n13. Magnetic Effects Of Current and Magnetism"));
        list.add(new Phy("\n14. Electromagnetic Induction and Alternating Currents"));
        list.add(new Phy("\n15. Electromagnetic Waves"));
        list.add(new Phy("\n16. Optics"));
        list.add(new Phy("\n17. Dual Nature Of Matter and radiation"));
        list.add(new Phy("\n18. Atoms and Nuclei"));
        list.add(new Phy("\n19. Electronic Devices"));
        list.add(new Phy("\n20. Communication Systems"));


        Phy_Adapter mAdapter2 = new Phy_Adapter(Main29Activity.this, list);
//        mAdapter = new Phy_Adapter(Main29Activity.this,mUploads);

        mAdapter2.setOnItemClickListener(Main29Activity.this);
        mRecyclerView.setAdapter(mAdapter2);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main29Activity.this, Main26Activity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void itemClicked(int position) {

        Phy myList = list.get(position);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("chapter", myList.getChapters());
        editor.apply();



        Intent intent = new Intent(Main29Activity.this, Main33Activity.class);
        startActivity(intent);

    }
}