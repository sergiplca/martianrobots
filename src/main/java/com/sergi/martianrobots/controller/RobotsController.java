package com.sergi.martianrobots.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergi.martianrobots.model.RobotsInput;
import com.sergi.martianrobots.repository.ScentRepository;
import com.sergi.martianrobots.service.RobotsProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping(path = "/v1")
public class RobotsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RobotsController.class);

    @Autowired
    RobotsProcessorService robotsProcessorService;

    @Autowired
    ScentRepository scentRepository;

    @PostMapping(path = "/run-robots",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<String> runRobots(@RequestBody String robotsInitialState)
            throws JsonProcessingException {

        LOGGER.info("[START] Received a request to move some robots...");

        ObjectMapper mapper = new ObjectMapper();
        RobotsInput input = mapper.readValue(robotsInitialState, RobotsInput.class);

        List<String> result = robotsProcessorService.processRobots(input);

        return new ResponseEntity<>(mapper.writeValueAsString(result), HttpStatus.OK);
    }

    @GetMapping(path = "/get-scents")
    public ResponseEntity<String> getScents() throws JsonProcessingException {

        LOGGER.info("[START] Received a request to show saved scents");

        ObjectMapper mapper = new ObjectMapper();
        var scents = scentRepository.findAll();

        return new ResponseEntity<>(mapper.writeValueAsString(scents), HttpStatus.OK);
    }
}
