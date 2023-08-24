package codes;

import java.util.ArrayList;
import java.util.List;



public class SolutionAndFitness  {
    private List<Integer> solution;
    private Double fitness;

    public List<SolutionAndFitness> generateSolutionAndCalFitness(int numberOfSolution,
                                                                  int numberOfCity,
                                                                  Double[][] distanceBetweenOfCites) {
        List<SolutionAndFitness> solutionAndFitnessList = new ArrayList<>();
        CityPosition cityPosition = new CityPosition();
        Double fitness;

        for (int i = 1; i <= numberOfSolution; i++) {
            List<Integer> solution = new ArrayList<>();
            for (int j = 0; j < numberOfCity; j++) {
                solution.add(j);
            }
            SolutionAndFitness solutionAndFitness = new SolutionAndFitness();
            java.util.Collections.shuffle(solution);
            solutionAndFitness.setSolution(solution);
            fitness =cityPosition.getFitnessSolution(solution,distanceBetweenOfCites);
            solutionAndFitness.setFitness(fitness);
            solutionAndFitnessList.add(solutionAndFitness);
        }
        return solutionAndFitnessList;
    }

    public List<Integer> getSolution() {
        return solution;
    }

    public void setSolution(List<Integer> solution) {
        this.solution = solution;
    }

    public Double getFitness() {
        return fitness;
    }

    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }
}
