package codes;

import java.util.ArrayList;
import java.util.List;

public class BestSolution {

    private Double fitness;
    private List<Integer> bestSolution;

    public List<SolutionAndFitness> concatPopulationAndMiddleOneAndSort(List<SolutionAndFitness> solutionAndFitnessList,
                                                                        List<SolutionAndFitness> listOfMutationSolutions,
                                                                        int numberOfSolution) {

        List<SolutionAndFitness> middlePopulationList = new ArrayList<>();
        for (SolutionAndFitness solutionAndFitness1 : solutionAndFitnessList)
            middlePopulationList.add(solutionAndFitness1);
        for (SolutionAndFitness solutionAndFitness2 : listOfMutationSolutions)
            middlePopulationList.add(solutionAndFitness2);


        SortedSolution sortedSolution = new SortedSolution();
        List<SolutionAndFitness> betterSolutionSortedList = sortedSolution.getSortedSolution(middlePopulationList);

        List<SolutionAndFitness> bestSolutionAndFitnessSorted = betterSolutionSortedList.subList(0, numberOfSolution);

        return bestSolutionAndFitnessSorted;
    }

    public Double getFitness() {
        return fitness;
    }

    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }

    public List<Integer> getBestSolution() {
        return bestSolution;
    }

    public void setBestSolution(List<Integer> bestSolution) {
        this.bestSolution = bestSolution;
    }
}
