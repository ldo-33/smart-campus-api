package resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.Sensor;
import models.SensorReading;
import storage.DataStore;

import java.util.Collection;
import java.util.UUID;
import java.util.ArrayList;

/**
 * Handles sensor-related API endpoints.
 */
@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {

    // GET all sensors (with optional filtering)
    @GET
    public Collection<Sensor> getSensors(@QueryParam("type") String type) {

        if (type == null) {
            return DataStore.sensors.values();
        }

        Collection<Sensor> filtered = new ArrayList<>();

        for (Sensor sensor : DataStore.sensors.values()) {
            if (sensor.type != null && sensor.type.equalsIgnoreCase(type)) {
                filtered.add(sensor);
            }
        }

        return filtered;
    }

    // POST create a sensor
    @POST
    public Response createSensor(Sensor sensor) {

        sensor.id = UUID.randomUUID().toString();
        DataStore.sensors.put(sensor.id, sensor);

        return Response.status(Response.Status.CREATED).entity(sensor).build();
    }

    // GET sensor by ID
    @GET
    @Path("/{id}")
    public Response getSensorById(@PathParam("id") String id) {

        Sensor sensor = DataStore.sensors.get(id);

        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(sensor).build();
    }

    // GET readings for a specific sensor
    @GET
    @Path("/{id}/readings")
    public Response getReadingsBySensor(@PathParam("id") String id) {

        Sensor sensor = DataStore.sensors.get(id);

        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Collection<SensorReading> results = new ArrayList<>();

        for (SensorReading reading : DataStore.readings.values()) {
            if (reading.sensorId != null && reading.sensorId.equals(id)) {
                results.add(reading);
            }
        }

        return Response.ok(results).build();
    }
}