package algorithm;

import bean.Coordinate;
import bean.Individual;
import bean.Person;
import parameter.FindingBestRoadParameters;
import parameter.GeneticAlgorithmParameters;
import util.MathUtil;
import util.SortUtil;

import java.util.*;
import java.util.stream.Collectors;

public class GeneticAlgorithm {

    private static final ArrayList<Integer> directions = new ArrayList<Integer>(){{ add(1); add(2); add(3); add(4); }};
    private MathUtil mathUtil;
    private SortUtil sortUtil;
    private FindingBestRoadParameters parameters;
    private GeneticAlgorithmParameters geneticAlgorithmParameters;

    public GeneticAlgorithm(FindingBestRoadParameters parameters, GeneticAlgorithmParameters geneticAlgorithmParameters){
        this.mathUtil = new MathUtil();
        this.sortUtil = new SortUtil();
        this.parameters = parameters;
        this.geneticAlgorithmParameters = geneticAlgorithmParameters;
    }

    public ArrayList<Coordinate> applyGeneticAlgorithm(ArrayList<Person> people){
        ArrayList<Individual> initialPopulation = new ArrayList<>();

        for(int i=0; i<geneticAlgorithmParameters.getIndividualCount(); i++){
            initialPopulation.add(mathUtil.createRandomIndividual(parameters.getR(), parameters.getMx(), parameters.getMy(), directions));
        }

        int iterationPointer = 0;
        Individual individualToCross1 = null;
        Individual individualToCross2 = null;

        ArrayList<Individual> previousGeneration = new ArrayList<Individual>(){{addAll(initialPopulation);}};
        Individual bestIndividual = findBestIndividual(previousGeneration, people);
        while (iterationPointer < geneticAlgorithmParameters.getMaxIterationCount()){
            ArrayList<Individual> newGeneration = new ArrayList<>();
            for(int i=0; i<geneticAlgorithmParameters.getIndividualCount(); i++){
                individualToCross1 = randomlySelectIndividual(people, previousGeneration);
                individualToCross2 = randomlySelectIndividual(people, previousGeneration);

                Individual newChild = reproduce(individualToCross1, individualToCross2);
                newChild = mutate(newChild, geneticAlgorithmParameters.getMutationRate());
                newGeneration.add(newChild);
            }

            newGeneration.add(bestIndividual);
            bestIndividual = findBestIndividual(newGeneration, people);
            iterationPointer++;
        }

        return mathUtil.findCoordinates(bestIndividual);
    }

    public Individual findBestIndividual(ArrayList<Individual> population, ArrayList<Person> people){
        Individual bestIndividual = population.get(0);

        int leastDistance = fitnessFunction(people, population.get(0));
        for(Individual indivudual : population){
            int distance = fitnessFunction(people, indivudual);
            if(leastDistance > distance){
                leastDistance = distance;
                bestIndividual = indivudual;
            }
        }

        return bestIndividual;
    }

    public Individual mutate(Individual child, Double mutationRate){
        Individual mutant = copyIndividual(child);

        int geneCountToMutate = ((Double) (mutationRate * child.getDirections().size())).intValue();

        Random random = new Random();
        for(int i=0; i<geneCountToMutate; i++){
            int geneIndexToMutate = random.nextInt(child.getDirections().size());
            int direction = directions.get(random.nextInt(directions.size()));

            if(geneCountToMutate != 0){
                while (Math.abs(child.getDirections().get(geneIndexToMutate-1) - direction) == 2){
                    direction = directions.get(random.nextInt(directions.size()));
                }
            }

            mutant.getDirections().set(geneIndexToMutate, direction);
        }

        return mutant;
    }

    public Individual copyIndividual(Individual individual){
        Individual copy = new Individual(individual.getStartX(), individual.getStartY());

        ArrayList<Integer> coordinates = new ArrayList<Integer>(){{addAll(individual.getDirections());}};
        copy.setDirections(coordinates);

        return copy;
    }

    public Individual reproduce(Individual child1, Individual child2){
        Random random = new Random();
        Individual newChild = new Individual(random.nextInt(parameters.getMx()), random.nextInt(parameters.getMy()));

        ArrayList<Integer> directions = new ArrayList<>();
        int splitPoint = new Random().nextInt(child1.getDirections().size()-1) + 1;

        while (Math.abs(child1.getDirections().get(splitPoint-1) - child2.getDirections().get(splitPoint)) == 2){
            splitPoint = new Random().nextInt(child1.getDirections().size()-1) + 1;
        }

        for(int i=0; i<splitPoint; i++){
            directions.add(child1.getDirections().get(i));
        }

        for(int i=splitPoint; i<child2.getDirections().size(); i++){
            directions.add(child2.getDirections().get(i));
        }

        newChild.getDirections().addAll(directions);
        return newChild;
    }

    public Individual randomlySelectIndividual(ArrayList<Person> people, ArrayList<Individual> individuals){
        int indexOfChoosen = 0;

        ArrayList<Integer> fitnessValues = new ArrayList<>();
        int totalValue = 0;
        for(int i=0; i<individuals.size(); i++){
            totalValue += i+1;
            fitnessValues.add(fitnessFunction(people, individuals.get(i)));
        }

        ArrayList<Integer> indexes = sortUtil.sortList(fitnessValues);

        int rand = new Random().nextInt(totalValue);
        int chancePointer = 0;
        for(int i=individuals.size()-1; i>=0; i--){
            chancePointer += i+1;

            if(chancePointer >= rand){
                indexOfChoosen = indexes.get(i);
                break;
            }
        }

        return individuals.get(indexOfChoosen);
    }

    public Integer fitnessFunction(ArrayList<Person> people, Individual individual){
        Integer resultPoint = 0;

        ArrayList<Coordinate> coordinates = mathUtil.findCoordinates(individual);
        if(checkIfIndividualHasError(coordinates)){
            return Integer.MAX_VALUE;
        }

        for (Person person : people){
            resultPoint += mathUtil.findDistance(person.getStartX(), person.getStartY(), coordinates);
            resultPoint += mathUtil.findDistance(person.getEndX(), person.getEndY(), coordinates);
            resultPoint += coordinates.size();
        }

        return resultPoint;
    }

    public boolean checkIfIndividualHasError(ArrayList<Coordinate> individual){

        for(Coordinate coordinate : individual){
            if(coordinate.getX() >= parameters.getMx() || coordinate.getX() < 0 || coordinate.getY() >= parameters.getMy() || coordinate.getY() < 0){
                return true;
            }
        }

        return false;
    }

}
