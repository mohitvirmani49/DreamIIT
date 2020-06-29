package com.mohit.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class Main32Activity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main32);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_me);
        back = (ImageButton) findViewById(R.id.back);
        List<Phy> list = new ArrayList<>();

        list.add(new Phy("\n1. Basic Concepts of Chemistry"));
        list.add(new Phy("\n2. States of Matter"));
        list.add(new Phy("\n3. Atomic Structure"));
        list.add(new Phy("\n4. Chemical Bonding"));
        list.add(new Phy("\n5. Chemical Thermodynamics"));
        list.add(new Phy("\n6. Solutions"));
        list.add(new Phy("\n7. Equilibrium"));
        list.add(new Phy("\n8. Redox Reactions and Electrochemistry"));
        list.add(new Phy("\n9. Chemical Kinetics"));
        list.add(new Phy("\n10. Surface Chemistry"));
        list.add(new Phy("\n11. Periodic Classification of Elements"));
        list.add(new Phy("\n12. Metallurgy"));
        list.add(new Phy("\n13. Hydrogen"));
        list.add(new Phy("\n14. S Block Elements"));
        list.add(new Phy("\n15. P Block Elements"));
        list.add(new Phy("\n16. D and F Block Elements"));
        list.add(new Phy("\n17. Coordination Compounds"));
        list.add(new Phy("\n18. Environmental Chemistry"));
        list.add(new Phy("\n19. General Organic Chemistry"));
        list.add(new Phy("\n20. Hydrocarbons"));
        list.add(new Phy("\n21. Organic Compounds Containing Halogens"));
        list.add(new Phy("\n22. Organic Compounds Containing Oxygen"));
        list.add(new Phy("\n23. Organic Compounds Containing Nitrogen"));
        list.add(new Phy("\n24. Polymers"));
        list.add(new Phy("\n25. Biomolecules"));
        list.add(new Phy("\n26. Chemistry in Everyday Life"));
        list.add(new Phy("\n27. Principles Related to Practical Chemistry"));


        Chem_Adapter mAdapter = new Chem_Adapter(list);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main32Activity.this, Main27Activity.class);
                startActivity(intent);
            }
        });

    }
}