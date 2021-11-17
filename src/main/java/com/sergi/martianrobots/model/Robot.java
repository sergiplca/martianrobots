package com.sergi.martianrobots.model;

import javax.persistence.*;

@Entity
@Table(name = "robots")
public class Robot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "robot_position")
    private String robotPosition;

    @Column(name = "instructions")
    private String instructions;

    public String getRobotPosition() {
        return robotPosition;
    }

    public void setRobotPosition(String robotPosition) {
        this.robotPosition = robotPosition;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
