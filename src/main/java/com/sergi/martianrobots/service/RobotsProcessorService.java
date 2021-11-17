package com.sergi.martianrobots.service;

import com.sergi.martianrobots.model.Execution;
import com.sergi.martianrobots.model.Robot;
import com.sergi.martianrobots.model.RobotsInput;
import com.sergi.martianrobots.repository.ExecutionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.Point;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


@Service
public class RobotsProcessorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RobotsProcessorService.class);

    @Autowired
    ExecutionRepository executionRepository;

    @Autowired
    RobotRunnerService robotRunnerService;

    private static final String DELIMITER = " ";

    private Point upperBounds;
    private List<String> finalPositions;
    private int lostRobots = 0;

    public List<String> processRobots(RobotsInput input) {

        finalPositions = new ArrayList<>();

        calculateUpperBounds(input.getTopCoordinates());
        LOGGER.info("Upper bounds: X=" + upperBounds.x + ", Y=" + upperBounds.y);

        for(Robot robot : input.getRobots()) {
            finalPositions.add(robotRunnerService.runRobot(robot, upperBounds));
        }

        saveExecution(input, finalPositions);
        return finalPositions;
    }

    private void calculateUpperBounds(String topCoordinates) {
        StringTokenizer tokenizer = new StringTokenizer(topCoordinates, DELIMITER);

        upperBounds = new Point(
                Integer.parseInt(tokenizer.nextToken()),
                Integer.parseInt(tokenizer.nextToken()));
    }

    private void saveExecution(RobotsInput input, List<String> finalPositions) {
        Execution e = new Execution(
                upperBounds.x,
                upperBounds.y,
                input.getRobots().size(),
                lostRobots,
                Timestamp.from(ZonedDateTime.now().toInstant()),
                String.join(",", finalPositions));

        executionRepository.save(e);
        LOGGER.info("Execution saved successfully");
    }
}
