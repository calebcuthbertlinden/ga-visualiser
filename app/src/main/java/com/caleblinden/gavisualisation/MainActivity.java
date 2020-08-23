package com.caleblinden.gavisualisation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caleblinden.gavisualisation.GeneticAlgorithm.FitnessCalculation;
import com.caleblinden.gavisualisation.GeneticAlgorithm.GeneticAlgorithm;
import com.caleblinden.gavisualisation.GeneticAlgorithm.Individual;
import com.caleblinden.gavisualisation.GeneticAlgorithm.Population;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String MY_PREFS_NAME = "MyPref";

    private String inputSolution;
    private Population myPop;
    private ArrayList<Population> populations;
    boolean forceStop = false;

    @BindView(R.id.tournamentSize) EditText tournamentSize;
    @BindView(R.id.mutationRate) EditText mutationRate;
    @BindView(R.id.uniformRate) EditText uniformRate;
    @BindView(R.id.solution_text) EditText solutionText;
    @BindView(R.id.populationSize) EditText populationSize;

    @BindView(R.id.generation_found) TextView generationFound;
    @BindView(R.id.solution_card) LinearLayout solutionCard;

    private static final String MUTATION_RATE = "MUTATION_RATE";
    private static final String TOURNAMENT_SIZE = "TOURNAMENT_SIZE";
    private static final String SOLUTION = "SOLUTION";
    private static final String POPULATION_SIZE = "POPULATION_SIZE";
    private static final String UNIFORM_RATE = "UNIFORM_RATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupScreen();
    }

    public static Intent getStartIntent(Context context, double mutationRate, double uniformRate, int tournamentSize, String solutionText, int populationSize) {
        Intent populationIntent = new Intent(context, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(MUTATION_RATE, mutationRate);
        bundle.putSerializable(TOURNAMENT_SIZE, tournamentSize);
        bundle.putSerializable(SOLUTION, solutionText);
        bundle.putSerializable(POPULATION_SIZE, populationSize);
        bundle.putSerializable(UNIFORM_RATE, uniformRate);

        populationIntent.putExtras(bundle);
        return populationIntent;
    }

    @OnClick(R.id.initiateAlgorithm)
    public void initiateAlgorithm() {
        setupAlgorithm();

        while (!forceStop && myPop.getFittest().getFitness() < FitnessCalculation.getMaxFitness()) {
            System.out.println(getResources().getString(R.string.generation_label)
                    .concat(String.valueOf(populations.size()))
                    .concat(getResources().getString(R.string.fitness_label))
                    .concat(String.valueOf(myPop.getFittest().getFitness())));

            populations.add(new Population(myPop.clonePopulation()));
            myPop = GeneticAlgorithm.evolvePopulation(myPop);
        }

        solutionCard.setVisibility(View.VISIBLE);
        generationFound.setText(getResources().getString(R.string.generation_label)
                .concat(String.valueOf(populations.size())));

        System.out.println("Solution found!");
        System.out.println("Generation: " + populations.size());
        System.out.println("Genes:");
        System.out.println(myPop.getFittest());
    }

    @OnClick(R.id.stopAlgorithm)
    public void stopAlgorithm() {
        forceStop = true;
    }

    @OnClick(R.id.btn_visualise)
    public void visualise() {
        Intent visualiseIntent = ViewGenerationsActivity.getStartIntent(this, populations, inputSolution);
        startActivity(visualiseIntent);
    }

    private void setupAlgorithm() {
        inputSolution = solutionText.getText().toString();
        FitnessCalculation.setSolution(inputSolution);
        Individual.setDefaultGeneLength(inputSolution.length());

        GeneticAlgorithm.setupParameters(Double.parseDouble(uniformRate.getText().toString()), Double.parseDouble(mutationRate.getText().toString()), Integer.parseInt(tournamentSize.getText().toString()));
        myPop = new Population(Integer.parseInt(populationSize.getText().toString()), true);
        populations = new ArrayList<>();
    }

    private void setupScreen() {
        uniformRate.setText(getIntent().getSerializableExtra(UNIFORM_RATE).toString());
        mutationRate.setText(getIntent().getSerializableExtra(MUTATION_RATE).toString());
        tournamentSize.setText(getIntent().getSerializableExtra(TOURNAMENT_SIZE).toString());
        populationSize.setText(getIntent().getSerializableExtra(POPULATION_SIZE).toString());
        setPrefs();
    }

    private void setPrefs() {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean("SeenOnboarding", true);
        editor.apply();
    }
}
