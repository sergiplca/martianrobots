package com.sergi.martianrobots.service;

import com.sergi.martianrobots.model.Robot;
import com.sergi.martianrobots.util.Movement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.awt.Point;
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

    public String runRobot(Robot robot, Point upperBounds) {

        LOGGER.info("Moving robot...");
        LOGGER.info("Starting position: " + robot.getRobotPosition());

        loadStartingPosition(robot.getRobotPosition());
        List<String> instructions = loadInstructions(robot.getInstructions());
        for (String instruction : instructions) {
            if ("F".equals(instruction)) {
                currentCoords = Movement.advance(currentCoords, currentOrientation);
            } else {
                currentOrientation = Movement.rotate(instruction, currentOrientation);
            }
            robot.setRobotPosition(currentCoords.x + " " + currentCoords.y + " " + currentOrientation);
            LOGGER.debug("New position: X=" + currentCoords.x + " Y=" + currentCoords.y + " O=" + currentOrientation);
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
}
