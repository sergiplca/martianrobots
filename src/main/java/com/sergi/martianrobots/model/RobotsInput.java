package com.sergi.martianrobots.model;

import java.util.List;

public class RobotsInput {

    private String topCoordinates;
    private List<Robot> robots;

    public String getTopCoordinates() {
        return topCoordinates;
    }

    public void setTopCoordinates(String topCoordinates) {
        this.topCoordinates = topCoordinates;
    }

    public List<Robot> getRobots() {
        return robots;
    }

    public void setRobots(List<Robot> robots) {
        this.robots = robots;
    }
}
