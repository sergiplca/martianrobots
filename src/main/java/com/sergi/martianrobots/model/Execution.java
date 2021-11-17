package com.sergi.martianrobots.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "robots")
    private List<Robot> robots;

    @Column(name = "lost_robots")
    private int lostRobots;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "result")
    private String finalPositions;

    public Execution(int upperBoundXCoord, int upperBoundYCoord, List<Robot> robots, int lostRobots, Timestamp timestamp, String finalPositions) {
        this.upperBoundXCoord = upperBoundXCoord;
        this.upperBoundYCoord = upperBoundYCoord;
        this.robots = robots;
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

    public List<Robot> getRobots() {
        return robots;
    }

    public void setRobots(List<Robot> robots) {
        this.robots = robots;
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
