package com.caleblinden.gavisualisation.GeneticAlgorithm;

import java.io.Serializable;
import java.util.ArrayList;

public class Population implements Serializable {

    private ArrayList<Individual> individuals;
    private int populationSize;

    public Population(int populationSize, boolean initialise) {
        this.populationSize = populationSize;
        individuals = new ArrayList<>(populationSize);

        if (initialise) {
            for (int i = 0; i < populationSize; i++) {
                Individual newIndividual = new Individual();
                newIndividual.generateIndividual();
                saveIndividual(i, newIndividual);
            }
        }
    }

    public Population(ArrayList<Individual> individuals) {
        this.populationSize = individuals.size();
        this.individuals = individuals;
    }

    public Individual getIndividual(int index) {
        return individuals.get(index);
    }

    public Individual getFittest() {
        Individual fittest = individuals.get(0);
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    public Individual getWorst() {
        Individual worst = individuals.get(0);
        for (int i = 0; i < size(); i++) {
            if (worst.getFitness() >= getIndividual(i).getFitness()) {
                worst = getIndividual(i);
            }
        }
        return worst;
    }

    public int size() {
        return individuals.size();
    }

    public void saveIndividual(int index, Individual indiv) {
        individuals.add(index, indiv);
    }

    public ArrayList<Individual> getIndividuals() {
        return individuals;
    }

    public ArrayList<Individual> clonePopulation() {
        ArrayList<Individual> newPop = new ArrayList<>(populationSize);

        for (int i = 0; i < populationSize; i++) {
            Individual individual = new Individual();
            for (int x = 0; x < individual.size(); x++) {
                individual.setGene(x, individuals.get(i).getGene(x));
            }
            newPop.add(individual);
        }
        return newPop;
    }

}
