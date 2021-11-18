package com.sergi.martianrobots.model;

import java.awt.Point;
import java.util.List;

public class RobotState {

    private List<String> instructions;
    private Point currentCoords;
    private String currentOrientation;
    private Point previousCoords;
    private String previousOrientation;
    private boolean isLost;

    public RobotState() {

    }

    public RobotState(List<String> instructions, Point currentCoords, String currentOrientation, Point previousCoords, String previousOrientation, boolean isLost) {
        this.instructions = instructions;
        this.currentCoords = currentCoords;
        this.currentOrientation = currentOrientation;
        this.previousCoords = previousCoords;
        this.previousOrientation = previousOrientation;
        this.isLost = isLost;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public Point getCurrentCoords() {
        return currentCoords;
    }

    public void setCurrentCoords(Point currentCoords) {
        this.currentCoords = currentCoords;
    }

    public String getCurrentOrientation() {
        return currentOrientation;
    }

    public void setCurrentOrientation(String currentOrientation) {
        this.currentOrientation = currentOrientation;
    }

    public Point getPreviousCoords() {
        return previousCoords;
    }

    public void setPreviousCoords(Point previousCoords) {
        this.previousCoords = previousCoords;
    }

    public String getPreviousOrientation() {
        return previousOrientation;
    }

    public void setPreviousOrientation(String previousOrientation) {
        this.previousOrientation = previousOrientation;
    }

    public boolean isLost() {
        return isLost;
    }

    public void setLost(boolean lost) {
        isLost = lost;
    }

    @Override
    public String toString() {
        if (isLost) {
            return previousCoords.x + " " + previousCoords.y + " " + currentOrientation +  " LOST";
        } else {
            return currentCoords.x + " " + currentCoords.y + " " + currentOrientation;
        }
    }
}
