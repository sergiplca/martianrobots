package com.sergi.martianrobots.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public final class Movement {

    private static final Logger LOGGER = LoggerFactory.getLogger(Movement.class);

    public static Point advance(Point currentCoords, String currentOrientation) {

        LOGGER.debug("Current position: X=" + currentCoords.x + " Y=" + currentCoords.y + " O=" + currentOrientation);

        switch (currentOrientation) {
            case "N": return new Point(currentCoords.x, currentCoords.y + 1);
            case "S": return new Point(currentCoords.x, currentCoords.y - 1);
            case "E": return new Point(currentCoords.x + 1, currentCoords.y);
            case "W": return new Point(currentCoords.x - 1, currentCoords.y);
        }
        return null;
    }

    public static String rotate(String direction, String orientation) {
        switch (direction) {
            case "L": return rotateLeft(orientation);
            case "R": return rotateRight(orientation);
        }
        return null;
    }

    private static String rotateLeft(String orientation) {
        switch (orientation) {
            case "N": return "W";
            case "E": return "N";
            case "S": return "E";
            case "W": return "S";
        }
        return null;
    }

    private static String rotateRight(String orientation) {
        switch (orientation) {
            case "N": return "E";
            case "E": return "S";
            case "S": return "W";
            case "W": return "N";
        }
        return null;
    }
}
