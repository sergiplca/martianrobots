package com.sergi.martianrobots.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "executions")
public class Execution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "upper_bound_x_coord")
    private int upperBoundXCoord;

    @Column(name = "upper_bound_y_coord")
    private int upperBoundYCoord;

    @Column(name = "robots_number")
    private int robotsNumber;

    @Column(name = "lost_robots")
    private int lostRobots;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "result")
    private String finalPositions;

    public Execution(int upperBoundXCoord, int upperBoundYCoord, int robotsNumber, int lostRobots, Timestamp timestamp, String finalPositions) {
        this.upperBoundXCoord = upperBoundXCoord;
        this.upperBoundYCoord = upperBoundYCoord;
        this.robotsNumber = robotsNumber;
        this.lostRobots = lostRobots;
        this.timestamp = timestamp;
        this.finalPositions = finalPositions;
    }

    public int getUpperBoundXCoord() {
        return upperBoundXCoord;
    }

    public void setUpperBoundXCoord(int upperBoundXCoord) {
        this.upperBoundXCoord = upperBoundXCoord;
    }

    public int getUpperBoundYCoord() {
        return upperBoundYCoord;
    }

    public void setUpperBoundYCoord(int upperBoundYCoord) {
        this.upperBoundYCoord = upperBoundYCoord;
    }

    public int getRobots() {
        return robotsNumber;
    }

    public void setRobots(int robotsNumber) {
        this.robotsNumber = robotsNumber;
    }

    public int getLostRobots() {
        return lostRobots;
    }

    public void setLostRobots(int lostRobots) {
        this.lostRobots = lostRobots;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getFinalPositions() {
        return finalPositions;
    }

    public void setFinalPositions(String finalPositions) {
        this.finalPositions = finalPositions;
    }
}
