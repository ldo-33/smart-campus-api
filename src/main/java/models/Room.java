package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a room within the system.
 * Each room can contain multiple sensors.
 */
public class Room {

    // Unique identifier for the room
    public String id;

    // Name of the room (e.g., "Library", "Lab 1")
    public String name;

    // Maximum capacity of the room
    public int capacity;

    // List of sensor IDs associated with this room
    public List<String> sensorIds = new ArrayList<>();
}