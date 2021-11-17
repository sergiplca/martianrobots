package com.sergi.martianrobots.service;

import com.sergi.martianrobots.model.Robot;
import com.sergi.martianrobots.model.Scent;
import com.sergi.martianrobots.util.Movement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Point currentCoords;
    private String currentOrientation;

    List<Scent> scents = new ArrayList<>();

    public String runRobot(Robot robot, Point upperBounds) {

        LOGGER.info("Moving robot...");
        LOGGER.info("Starting position: " + robot.getRobotPosition());

        Point newCoords = new Point();
        loadStartingPosition(robot.getRobotPosition());
        List<String> instructions = loadInstructions(robot.getInstructions());

        for (String instruction : instructions) {
            if ("F".equals(instruction)) {
                if (!checkScent()) {
                    newCoords = Movement.advance(currentCoords, currentOrientation);
                    if (checkIfLost(upperBounds, newCoords)) {
                        robot.setRobotPosition(currentCoords.x + " " + currentCoords.y + " " + currentOrientation + " LOST");
                        scents.add(new Scent(currentCoords.x, currentCoords.y, currentOrientation));
                        break;
                    } else {
                        robot.setRobotPosition(newCoords.x + " " + newCoords.y + " " + currentOrientation);
                        currentCoords = newCoords;
                    }
                }
            } else {
                currentOrientation = Movement.rotate(instruction, currentOrientation);
                robot.setRobotPosition(currentCoords.x + " " + currentCoords.y + " " + currentOrientation);
            }
            LOGGER.debug("New position: X=" + newCoords.x + " Y=" + newCoords.y + " O=" + currentOrientation);
        }

        LOGGER.info("Final position: " + robot.getRobotPosition());
        return robot.getRobotPosition();
    }

    private void loadStartingPosition(String robotPosition) {
        StringTokenizer tokenizer = new StringTokenizer(robotPosition, COORDS_DELIMITER);

        currentCoords = new Point(
                Integer.parseInt(tokenizer.nextToken()),
                Integer.parseInt(tokenizer.nextToken()));
        currentOrientation = tokenizer.nextToken();
    }

    private List<String> loadInstructions (String instructions) {
        return Stream.of(instructions.split(INSTRUCTIONS_DELIMITER))
                .collect(Collectors.toList());
    }

    private boolean checkScent() {
        return scents.contains(new Scent(currentCoords.x, currentCoords.y, currentOrientation));
    }

    private boolean checkIfLost(Point upperBounds, Point newCoords) {
        return (newCoords.x < 0
                || newCoords.y < 0
                || newCoords.x > upperBounds.x
                || newCoords.y > upperBounds.y);
    }
}
