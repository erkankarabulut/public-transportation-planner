package bean;

import java.util.ArrayList;

public class Individual {

    private int startX;
    private int startY;
    private ArrayList<Integer> directions;

    public Individual(int startX, int startY) {
        this.directions = new ArrayList<>();
        this.startX = startX;
        this.startY = startY;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public ArrayList<Integer> getDirections() {
        return directions;
    }

    public void setDirections(ArrayList<Integer> directions) {
        this.directions = directions;
    }

    @Override
    public String toString() {
        return "Individual{" +
                "startX=" + startX +
                ", startY=" + startY +
                ", directions=" + directions +
                '}';
    }
}
