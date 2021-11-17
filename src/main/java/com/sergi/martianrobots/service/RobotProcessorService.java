package com.sergi.martianrobots.service;

import com.sergi.martianrobots.model.RobotsInput;
import com.sergi.martianrobots.repository.ExecutionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


@Service
public class RobotProcessorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RobotProcessorService.class);

    @Autowired
    ExecutionRepository executionRepository;

    private static final String DELIMITER = " ";

    private Point upperBounds;

    public List<String> processRobots(RobotsInput input) {

        List<String> finalPositions = new ArrayList<>();

        calculateUpperBounds(input.getTopCoordinates());
        LOGGER.info("Upper bounds: " + upperBounds.toString());

        return finalPositions;
    }

    private void calculateUpperBounds(String topCoordinates) {
        StringTokenizer tokenizer = new StringTokenizer(topCoordinates, DELIMITER);

        upperBounds = new Point(
                Integer.parseInt(tokenizer.nextToken()),
                Integer.parseInt(tokenizer.nextToken()));
    }
}
