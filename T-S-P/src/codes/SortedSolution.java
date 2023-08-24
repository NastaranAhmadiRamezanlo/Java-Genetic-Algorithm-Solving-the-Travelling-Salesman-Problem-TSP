package codes;

import java.util.ArrayList;
import java.util.List;

public class SortedSolution {
    public List<SolutionAndFitness> getSortedSolution(List<SolutionAndFitness> middlePopulationList) {


        List<Double> list = new ArrayList<>();
        List<SolutionAndFitness> finalList = new ArrayList<>();
        for (SolutionAndFitness bestSolution : middlePopulationList) {
            Double solutionList = bestSolution.getFitness();
            list.add(solutionList);
        }

        List<Integer> list2 = list.stream().sorted().map(i -> {
            int j = list.indexOf(i);
            list.set(j, 0.0);
            return j;
        }).toList();

        for (int k = 0; k < list2.size(); k++)
            finalList.add(middlePopulationList.get(list2.get(k)));
        return finalList;
    }
}