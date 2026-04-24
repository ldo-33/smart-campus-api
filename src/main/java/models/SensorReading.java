package models;

import java.time.LocalDateTime;

/**
 * Represents a single reading from a sensor.
 */
public class SensorReading {

    // unique id for the reading
    public String id;

    // sensor this reading belongs to
    public String sensorId;

    // recorded value
    public double value;

    // time the reading was taken
    public LocalDateTime timestamp;
}
