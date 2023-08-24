package codes;

import java.util.ArrayList;
import java.util.List;

public class TSP {
    /**
     * 1 generate mutation solutions of initial population
     * 2 combine two solutions list (parents(solutionAndFitnessList) + childs(listOfMutationSolutions))
     * as one list(final list or middle population)
     *
     * 3 sort final list based on fitness  value
     * 4 select popSize(solutionAndFitnessList) of top of sorted list
     * 5 while best solution not find. go to step 1
     */
    public static void main(String args[]) {
        //parameters
        int numberOfSolution = 200;
        int numberOfCity = 100;
        int mutationRate = 50;
        int maximumGeneration = 30000;
        int numberOfMutation = Math.round((mutationRate * numberOfSolution) / 100);
        int length = 700;

        List<Double> curveValues = new ArrayList<>();

        //objects
        CityPosition cityPosition = new CityPosition();
        List<City> cityList = cityPosition.generateLocation(numberOfCity, length);
        Double[][] distanceBetweenOfCites = cityPosition.calculateDistanceMatrix(cityList, numberOfCity);

        SolutionAndFitness solutionAndFitness = new SolutionAndFitness();
        BestSolution bestSolutionObj = new BestSolution();
        Mutation mutation = new Mutation();

        //initial population
        List<SolutionAndFitness> solutionAndFitnessList = solutionAndFitness.generateSolutionAndCalFitness(numberOfSolution, numberOfCity, distanceBetweenOfCites);

        //Evolution solutions
        int generation = 1;
        while (generation <= maximumGeneration) {
            List<SolutionAndFitness> listOfMutationSolutions = mutation.getMutation(solutionAndFitnessList, numberOfMutation, distanceBetweenOfCites);


            solutionAndFitnessList = bestSolutionObj.concatPopulationAndMiddleOneAndSort(solutionAndFitnessList, listOfMutationSolutions, numberOfSolution);
            System.out.println(" current generation is : " + generation + " and best solution fitness is : " + solutionAndFitnessList.get(0).getFitness());
            generation++;
            if (generation % 400 == 0)
                curveValues.add(solutionAndFitnessList.get(0).getFitness());
        }


        List<Integer> bestSolution = solutionAndFitnessList.get(0).getSolution();

        //best solutions are found and finish.
        for (Integer integer : bestSolution) {
            System.out.print(integer + "  ");
        }
        System.out.println();

        GraphPanel.curveValues = curveValues;
        GraphPanel.createAndShowGui();

        new DirectionTSP(cityList, bestSolution);

        DirectionTSP.createAndShowGui();
    }
}