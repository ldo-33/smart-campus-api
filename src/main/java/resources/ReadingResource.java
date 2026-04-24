package resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.SensorReading;
import storage.DataStore;

import java.util.Collection;
import java.util.UUID;

/**
 * Handles sensor reading endpoints.
 */
@Path("/readings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReadingResource {

    // GET all readings
    @GET
    public Collection<SensorReading> getReadings() {
        return DataStore.readings.values();
    }

    // POST create a reading (with validation)
    @POST
    public Response createReading(SensorReading reading) {

        // check sensor exists
        if (reading.sensorId == null || !DataStore.sensors.containsKey(reading.sensorId)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        reading.id = UUID.randomUUID().toString();
        DataStore.readings.put(reading.id, reading);

        return Response.status(Response.Status.CREATED).entity(reading).build();
    }
}