package storage;

import models.*;

import java.util.*;

/**
 * Simple in-memory storage for the application.
 * Acts like a database for rooms, sensors, and readings.
 */
public class DataStore {

    // store all rooms
    public static Map<String, Room> rooms = new HashMap<>();

    // store all sensors
    public static Map<String, Sensor> sensors = new HashMap<>();

    // store all readings
    public static Map<String, SensorReading> readings = new HashMap<>();
}