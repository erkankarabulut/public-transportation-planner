package parameter;

import algorithm.GeneticAlgorithm;

import java.util.Properties;

public class GeneticAlgorithmParameters {

    private int individualCount;
    private int maxIterationCount;
    private double mutationRate;

    public GeneticAlgorithmParameters(FindingBestRoadParameters parameters){
        this.individualCount = parameters.getP() * 2;
        this.mutationRate = 0.05;
        this.maxIterationCount = 300000;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public int getIndividualCount() {
        return individualCount;
    }

    public void setIndividualCount(int individualCount) {
        this.individualCount = individualCount;
    }

    public int getMaxIterationCount() {
        return maxIterationCount;
    }

    public void setMaxIterationCount(int maxIterationCount) {
        this.maxIterationCount = maxIterationCount;
    }
}
