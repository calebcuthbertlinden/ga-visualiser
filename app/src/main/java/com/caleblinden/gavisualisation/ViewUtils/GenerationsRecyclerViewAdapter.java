package com.caleblinden.gavisualisation.ViewUtils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caleblinden.gavisualisation.GeneticAlgorithm.Population;
import com.caleblinden.gavisualisation.R;

import java.util.List;

public class GenerationsRecyclerViewAdapter extends RecyclerView.Adapter<GenerationsRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Population> population;
    private String solution;

    public GenerationsRecyclerViewAdapter(Context context, List<Population> population, String solution) {
        this.context = context;
        this.population = population;
        this.solution = solution;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.generation_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setTag(population.get(position));
        final Population myPop = population.get(position);

        holder.fitness.setText("Best Fitness : ".concat(String.valueOf(myPop.getFittest().getFitness())));
        holder.genes.setText(myPop.getFittest().toString());
        holder.generation.setText("Generation: ".concat(String.valueOf(position)));
        holder.fitnessWorst.setText("Worst Fitness: ".concat(String.valueOf(myPop.getWorst().getFitness())));
        holder.genesWorst.setText(myPop.getWorst().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return population.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView generation;
        TextView genes;
        TextView fitness;
        TextView genesWorst;
        TextView fitnessWorst;

        public ViewHolder(View itemView) {
            super(itemView);

            generation = (TextView) itemView.findViewById(R.id.individual_generation);
            genes = (TextView) itemView.findViewById(R.id.individual_genes_best);
            fitness = (TextView) itemView.findViewById(R.id.individual_fitness_best);
            genesWorst = (TextView) itemView.findViewById(R.id.individual_genes_worst);
            fitnessWorst = (TextView) itemView.findViewById(R.id.individual_fitness_worst);
        }
    }


}
