package util;

import bean.Coordinate;
import bean.Individual;
import bean.Person;

import java.util.ArrayList;
import java.util.Random;

public class MathUtil {

    public ArrayList<Person> placePeople(int peopleCount, int x, int y){
        ArrayList<Person> places = new ArrayList<>();
        Random random = new Random();

        int randStartX = 0;
        int randStartY = 0;
        int randEndX = 0;
        int randEndY = 0;

        for(int i=0; i<peopleCount; i++){
            randStartX = random.nextInt(x);
            randEndX = random.nextInt(x);
            randStartY = random.nextInt(y);
            randEndY = random.nextInt(y);

            while (randStartX == randEndX && randStartY == randEndY){
                randStartX = random.nextInt(x);
                randEndX = random.nextInt(x);
                randStartY = random.nextInt(y);
                randEndY = random.nextInt(y);
            }

            places.add(new Person(randStartX, randEndX, randStartY, randEndY));
        }

        return places;
    }

    public Individual createRandomIndividual(int size, int x, int y, ArrayList<Integer> possibilities){
        Random random = new Random();
        Individual individual = new Individual(random.nextInt(x), random.nextInt(y));

        ArrayList<Integer> directions = new ArrayList<>();
        directions.add(possibilities.get(random.nextInt(possibilities.size())));
        for(int i=1; i<size-1; i++){
            int direction = possibilities.get(random.nextInt(possibilities.size()));
            while (Math.abs(direction - directions.get(i-1)) == 2){
                direction = possibilities.get(random.nextInt(possibilities.size()));
            }

            directions.add(possibilities.get(random.nextInt(possibilities.size())));
        }

        individual.getDirections().addAll(directions);
        return individual;
    }

    public ArrayList<Coordinate> findCoordinates(Individual individual){
        ArrayList<Coordinate> coordinates = new ArrayList<>();

        coordinates.add(new Coordinate(individual.getStartX(), individual.getStartY()));
        int currentX = individual.getStartX();
        int currentY = individual.getStartY();
        for (int i=0; i<individual.getDirections().size(); i++){
            int direction = individual.getDirections().get(i);

            // 1:left, 2:up, 3:right and 4:down
            switch (direction){
                case 1:
                    currentY--;
                    break;
                case 2:
                    currentX--;
                    break;
                case 3:
                    currentY++;
                    break;
                case 4:
                    currentX++;
                    break;
            }

            coordinates.add(new Coordinate(currentX, currentY));
        }

        return coordinates;
    }

    public Integer findDistance(int x, int y, ArrayList<Coordinate> road){
        int minDistance = 0;

        int currentDistance = 0;
        for (Coordinate coordinate : road){
            currentDistance = Math.abs(x-coordinate.getX()) + Math.abs(y-coordinate.getY());
            if(currentDistance < minDistance){
                minDistance = currentDistance;
            }
        }

        return minDistance;
    }

}
