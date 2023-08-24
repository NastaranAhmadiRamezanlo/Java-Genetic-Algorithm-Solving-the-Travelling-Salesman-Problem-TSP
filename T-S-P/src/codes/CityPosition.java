package codes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CityPosition {
    private double distance;
    private double sumDistanceCities = 0.0;

    public List<City> generateLocation(int numberOfCities, int lenght) {

        List<City> cityList = new ArrayList<>();
        for (int i = 1; i <= numberOfCities; i++) {
            City city = new City();
            city.setX(Math.random() * lenght + 10.0);
            city.setY(Math.random() * lenght + 10.0);
            cityList.add(city);
        }
        return cityList;
    }

    public double distance(City city1, City city2) {
        return Math.sqrt(Math.pow(city1.getX() - city2.getX(), 2) + Math.pow(city1.getY() - city2.getY(), 2));
    }

    public Double[][] calculateDistanceMatrix(List<City> cityList, int numberOfCities) {
        Double[][] distanceBetweenOfCites = new Double[numberOfCities][numberOfCities];
        for (int i = 0; i < numberOfCities; i++)
            for (int j = 0; j < numberOfCities; j++)
                distanceBetweenOfCites[i][j] = distance(cityList.get(i), cityList.get(j));
        return distanceBetweenOfCites;
    }

    public Double getFitnessSolution(List<Integer> sol, Double[][] distanceBetweenOfCites) {
        int indexSecondCity;
        int indexFirstCity;
        double sumDistanceSolution = 0.0;
        for (int i = 0; i < sol.size(); i++) {
            if (i < sol.size() - 1) {
                indexFirstCity = sol.get(i);//10
                indexSecondCity = sol.get(i + 1);//9
            } else {
                indexFirstCity = sol.get(sol.size() - 1);
                indexSecondCity = sol.get(0);
            }
            sumDistanceSolution += distanceBetweenOfCites[indexFirstCity][indexSecondCity];
        }
        return sumDistanceSolution;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
