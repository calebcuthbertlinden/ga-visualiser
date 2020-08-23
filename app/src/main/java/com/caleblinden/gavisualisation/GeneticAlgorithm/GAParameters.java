package com.caleblinden.gavisualisation.GeneticAlgorithm;

public enum GAParameters {
    MUTATION_RATE(1),
    UNIFORM_RATE(2),
    POPULATION_SIZE(0),
    ELITISM(4),
    TOURNAMENT_SIZE(3),
    SOLUTION(5);

    private final int parameterId;

    private GAParameters(int parameterId) {
        this.parameterId = parameterId;
    }

    public static GAParameters fromValue(int parameterId) {
        for (GAParameters ga : GAParameters.values()) {
            if (ga.parameterId == parameterId) {
                return ga;
            }
        }
        return null;
    }
}
