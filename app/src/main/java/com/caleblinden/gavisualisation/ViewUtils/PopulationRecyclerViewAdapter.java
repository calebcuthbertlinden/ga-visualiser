package com.caleblinden.gavisualisation.ViewUtils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caleblinden.gavisualisation.GeneticAlgorithm.Individual;
import com.caleblinden.gavisualisation.R;

import java.util.ArrayList;

public class PopulationRecyclerViewAdapter extends RecyclerView.Adapter<PopulationRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Individual> personUtils;
    private String solution;

    public PopulationRecyclerViewAdapter(Context context, ArrayList<Individual> personUtils, String solution) {
        this.context = context;
        this.personUtils = personUtils;
        this.solution = solution;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.population_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(personUtils.get(position));
        Individual individual = personUtils.get(position);

        holder.mutated.setText("Mutated: " + individual.getMutated());
        holder.fitness.setText("Fitness: " + String.valueOf(individual.getFitness() / individual.size() * 100));
        holder.genes.setText(individual.toString());
    }

    @Override
    public int getItemCount() {
        return personUtils.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView genes;
        TextView fitness;
        TextView mutated;

        ViewHolder(View itemView) {
            super(itemView);
            genes = (TextView) itemView.findViewById(R.id.genes);
            fitness = (TextView) itemView.findViewById(R.id.fitness);
            mutated = (TextView) itemView.findViewById(R.id.mutated);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
