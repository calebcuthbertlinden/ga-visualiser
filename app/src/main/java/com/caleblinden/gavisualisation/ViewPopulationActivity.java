package com.caleblinden.gavisualisation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.caleblinden.gavisualisation.GeneticAlgorithm.Individual;
import com.caleblinden.gavisualisation.ViewUtils.PopulationRecyclerViewAdapter;

import java.util.ArrayList;


public class ViewPopulationActivity extends AppCompatActivity {

    private static final String EXTRA_POPULATION = "population";
    private static final String EXTRA_SOLUTION = "solution";

    private RecyclerView population;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Individual> myPop;
    private String solution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vieww_population);
        population = (RecyclerView) findViewById(R.id.recyclerView);

        myPop = (ArrayList<Individual>) getIntent().getSerializableExtra(EXTRA_POPULATION);
        solution = getIntent().getStringExtra(EXTRA_SOLUTION);

        population.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        population.setLayoutManager(layoutManager);
        mAdapter = new PopulationRecyclerViewAdapter(this, myPop, solution);
        population.setAdapter(mAdapter);
    }

    public static Intent getStartIntent(Context context, ArrayList<Individual> individual, String solution) {
        Intent populationIntent = new Intent(context, ViewPopulationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("population", individual);
        bundle.putString("solution", solution);
        populationIntent.putExtras(bundle);
        return populationIntent;
    }
}
