package com.caleblinden.gavisualisation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.caleblinden.gavisualisation.GeneticAlgorithm.Population;
import com.caleblinden.gavisualisation.ViewUtils.GenerationsRecyclerViewAdapter;

import java.util.ArrayList;

public class ViewGenerationsActivity extends AppCompatActivity {

    private static final String EXTRA_POPULATIONS = "populations";
    private static final String EXTRA_SOLUTION = "solution";

    private RecyclerView population;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Population> populations;
    private String solution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_generations);
        population = (RecyclerView) findViewById(R.id.recyclerView);

        populations = (ArrayList<Population>) getIntent().getSerializableExtra(EXTRA_POPULATIONS);
        solution = getIntent().getStringExtra(EXTRA_SOLUTION);

        population.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        population.setLayoutManager(layoutManager);
        mAdapter = new GenerationsRecyclerViewAdapter(this, populations, solution);
        population.setAdapter(mAdapter);
    }

    public static Intent getStartIntent(Context context, ArrayList<Population> populations, String solution) {
        Intent populationIntent = new Intent(context, ViewGenerationsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_POPULATIONS, populations);
        bundle.putString(EXTRA_SOLUTION, solution);
        populationIntent.putExtras(bundle);
        return populationIntent;
    }
}
