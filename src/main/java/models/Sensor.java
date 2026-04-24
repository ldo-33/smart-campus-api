package models;

/**
 * Represents a sensor linked to a room.
 * Stores its type, status and latest reading.
 */
public class Sensor {

    // Unique identifier for the sensor
    public String id;

    // Type of sensor (e.g., temperature, humidity)
    public String type;

    // Current status of the sensor
    public SensorStatus status;

    // Latest recorded value from the sensor
    public double currentValue;

    // Name of the sensor
    public String name;

    // ID of the room this sensor belongs to
    public String roomId;

    // needed for JSON (Jersey)
    public Sensor() {
    }
}