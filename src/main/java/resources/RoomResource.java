package resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.Room;
import models.Sensor;
import storage.DataStore;

import java.util.Collection;
import java.util.UUID;
import java.util.ArrayList;

/**
 * handles room endpoints
 */
@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {

    // quick test to see if API is running
    @GET
    @Path("/ping")
    public String ping() {
        return "pong";
    }

    // get all rooms
    @GET
    public Collection<Room> getAllRooms() {
        return DataStore.rooms.values();
    }

    // create a new room
    @POST
    public Response createRoom(Room room) {

        // basic check so empty request doesn't break
        if (room == null || room.name == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        room.id = UUID.randomUUID().toString();

        // make sure sensor list exists
        if (room.sensorIds == null) {
            room.sensorIds = new ArrayList<>();
        }

        DataStore.rooms.put(room.id, room);

        return Response.status(Response.Status.CREATED)
                .entity(room)
                .build();
    }

    // get one room by id
    @GET
    @Path("/{id}")
    public Response getRoom(@PathParam("id") String id) {

        Room room = DataStore.rooms.get(id);

        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(room).build();
    }

    // delete a room
    @DELETE
    @Path("/{id}")
    public Response deleteRoom(@PathParam("id") String id) {

        Room room = DataStore.rooms.get(id);

        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // don't delete if room still has sensors
        if (room.sensorIds != null && !room.sensorIds.isEmpty()) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        DataStore.rooms.remove(id);

        return Response.noContent().build();
    }

    // add a sensor to a room
    @POST
    @Path("/{roomId}/sensors")
    public Response addSensorToRoom(@PathParam("roomId") String roomId, Sensor sensor) {

        Room room = DataStore.rooms.get(roomId);

        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // basic validation
        if (sensor == null || sensor.name == null || sensor.type == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        sensor.id = UUID.randomUUID().toString();
        sensor.roomId = roomId;

        DataStore.sensors.put(sensor.id, sensor);

        // make sure list exists before adding
        if (room.sensorIds == null) {
            room.sensorIds = new ArrayList<>();
        }

        room.sensorIds.add(sensor.id);

        return Response.status(Response.Status.CREATED)
                .entity(sensor)
                .build();
    }
}