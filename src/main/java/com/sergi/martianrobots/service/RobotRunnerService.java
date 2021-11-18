package com.sergi.martianrobots.service;

import com.sergi.martianrobots.model.Robot;
import com.sergi.martianrobots.model.RobotState;
import com.sergi.martianrobots.model.Scent;
import com.sergi.martianrobots.repository.ScentRepository;
import com.sergi.martianrobots.util.Movement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RobotRunnerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RobotRunnerService.class);

    private static final String COORDS_DELIMITER = " ";
    private static final String INSTRUCTIONS_DELIMITER = "";

    @Autowired
    ScentRepository scentRepository;

    private List<Scent> scents = new ArrayList<>();

    public RobotState runRobot(Robot robot, Point upperBounds) {

        LOGGER.info("Moving robot...");
        LOGGER.info("Starting position: " + robot.getRobotPosition());

        var robotState = fillInitialRobotState(robot);

        for (String instruction : robotState.getInstructions()) {
            if ("F".equals(instruction)) {
                if (!checkScent(robotState.getCurrentCoords().x, robotState.getCurrentCoords().y, robotState.getCurrentOrientation())) {
                    robotState.setPreviousCoords(robotState.getCurrentCoords());
                    robotState.setCurrentCoords(Movement.advance(robotState.getCurrentCoords(), robotState.getCurrentOrientation()));
                    if (checkIfLost(upperBounds, robotState)) {
                        robotState.setLost(true);
                        saveScent(robotState);
                        break;
                    }
                }
            } else {
                robotState.setPreviousOrientation(robotState.getCurrentOrientation());
                robotState.setCurrentOrientation(Movement.rotate(instruction, robotState.getCurrentOrientation()));
            }
        }

        LOGGER.info("Final position: " + robotState.toString());
        return robotState;
    }

    private RobotState fillInitialRobotState (Robot robot) {
        var initialState = new RobotState();

        initialState.setInstructions(Stream.of(robot.getInstructions()
                .split(INSTRUCTIONS_DELIMITER))
                .collect(Collectors.toList()));

        var tokenizer = new StringTokenizer(robot.getRobotPosition(), COORDS_DELIMITER);
        initialState.setCurrentCoords(new Point(
                Integer.parseInt(tokenizer.nextToken()),
                Integer.parseInt(tokenizer.nextToken())));

        initialState.setCurrentOrientation(tokenizer.nextToken());

        initialState.setPreviousCoords(initialState.getCurrentCoords());
        initialState.setPreviousOrientation(initialState.getCurrentOrientation());
        initialState.setLost(false);

        return initialState;
    }

    private boolean checkScent(int x, int y, String o) {
        return scentRepository.existsScent(x, y, o);
    }

    private void saveScent(RobotState state) {
        int x = state.getPreviousCoords().x;
        int y = state.getPreviousCoords().y;
        String o = state.getCurrentOrientation();
        if (!checkScent(x, y, o)) {
            scentRepository.save(new Scent(x, y, o));
        }
    }

    private boolean checkIfLost(Point upperBounds, RobotState state) {
        return (state.getCurrentCoords().x < 0
                || state.getCurrentCoords().y < 0
                || state.getCurrentCoords().x > upperBounds.x
                || state.getCurrentCoords().y > upperBounds.y);
    }
}
