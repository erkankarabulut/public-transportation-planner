package api.provider;

import algorithm.GeneticAlgorithm;
import bean.Coordinate;
import bean.Person;
import org.json.JSONArray;
import org.json.JSONObject;
import parameter.FindingBestRoadParameters;
import parameter.GeneticAlgorithmParameters;
import util.MathUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class FindingBestRoadProvider {

    public static JSONObject findBestRoad(HttpServletRequest request, FindingBestRoadParameters parameters){
        JSONObject result = new JSONObject();
        MathUtil mathUtil = new MathUtil();

        GeneticAlgorithmParameters geneticAlgorithmParameters = new GeneticAlgorithmParameters(parameters);
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(parameters, geneticAlgorithmParameters);
        ArrayList<Person> people = mathUtil.placePeople(parameters.getP(), parameters.getMx(), parameters.getMy());

        ArrayList<Coordinate> bestIndividual = geneticAlgorithm.applyGeneticAlgorithm(people);
        JSONArray coordinates = new JSONArray();

        for(int i=0; i<bestIndividual.size(); i++){
            JSONObject coordinatePair = new JSONObject();
            coordinatePair.put("x", bestIndividual.get(i).getX());
            coordinatePair.put("y", bestIndividual.get(i).getY());

            coordinates.put(coordinatePair);
        }

        JSONArray peopleLocations = new JSONArray();
        for(int i=0; i<people.size(); i++){
            JSONObject personLocation = new JSONObject();
            personLocation.put("startX", people.get(i).getStartX());
            personLocation.put("startY", people.get(i).getStartY());
            personLocation.put("endX", people.get(i).getEndX());
            personLocation.put("endY", people.get(i).getEndY());

            peopleLocations.put(personLocation);
        }

        result.put("peopleLocations", peopleLocations);
        result.put("transportationPath", coordinates);
        result.put("status", 200);
        return result;
    }

}

