# Smart Campus API

## Overview

This project implements a RESTful API for a Smart Campus system using Java and JAX-RS. The system manages rooms, sensors, and sensor readings within a campus environment.

The main aim of this project was to create an API that is easy to understand while still covering all the required functionality. I focused on structuring the system clearly, particularly around how rooms, sensors, and readings are connected, so that the relationships between them are easy to follow.

The API follows standard REST principles, using appropriate endpoints, HTTP methods, and status codes to ensure consistent and predictable behaviour.

---

## API Design

The system is based on three main resources:

- **Room** – represents a physical space within the campus
- **Sensor** – represents a device assigned to a room
- **SensorReading** – represents data collected from a sensor

In this project, these resources are linked using IDs, allowing sensors to be associated with rooms and readings to be associated with sensors. This reflects real-world relationships while keeping the design simple.

---

## Technologies Used

- Java
- Apache Tomcat
- Jakarta RESTful Web Services (JAX-RS)
- Maven

---

## Running the Application

1. Open the project in IntelliJ IDEA
2. Configure Apache Tomcat in “Edit Configurations”
3. Deploy the project as a `war exploded` artifact
4. Start the server

The API will be available at:

```
http://localhost:8080/api/v1
```

---

## Example Requests (cURL)

### Create a Room

```
curl -X POST http://localhost:8080/api/v1/rooms \
-H "Content-Type: application/json" \
-d '{"name":"Library","capacity":100}'
```

### Get All Rooms

```
curl http://localhost:8080/api/v1/rooms
```

### Add Sensor to Room

```
curl -X POST http://localhost:8080/api/v1/rooms/{roomId}/sensors \
-H "Content-Type: application/json" \
-d '{"type":"Temperature","status":"ACTIVE"}'
```

### Get Sensors (with filtering)

```
curl http://localhost:8080/api/v1/sensors?type=temperature
```

### Create Sensor Reading

```
curl -X POST http://localhost:8080/api/v1/readings \
-H "Content-Type: application/json" \
-d '{"sensorId":"SENSOR_ID","value":23.5}'
```

### Get Readings for a Sensor

```
curl http://localhost:8080/api/v1/sensors/{sensorId}/readings
```

---

## Design Decisions

The system uses in-memory data structures such as HashMaps and Lists to manage rooms, sensors, and sensor readings. This approach was chosen to meet the coursework requirement of not using a database while still allowing efficient access to data.

HashMaps are used because they allow quick retrieval of resources using unique IDs, which is important for handling API requests efficiently. Lists are used to maintain relationships, such as linking sensors to rooms, which keeps the structure simple and easy to follow. This also made the data easier to manage during development.

The overall design was kept intentionally simple to ensure the API is easy to understand while still correctly modelling the relationships between entities.

---

## Coursework Questions

### 1. Why did you choose your data structures?

HashMaps and Lists were used because they provide a simple and efficient way to manage data in memory. HashMaps allow fast access using unique IDs, while Lists help maintain relationships between entities such as rooms and sensors.

---

### 2. How does your API follow REST principles?

The API follows REST principles by using clear resource-based URLs and standard HTTP methods. GET is used for retrieval, POST for creation, and DELETE for removal. Appropriate HTTP status codes such as 200, 201, and 404 are used to indicate the outcome of requests.

---

### 3. How are relationships handled in your system?

In this system, relationships are handled using IDs. Sensors are linked to rooms using a roomId, and sensor readings are linked to sensors using a sensorId. This keeps the structure flexible and avoids unnecessary duplication.

---

### 4. What are the limitations of your implementation?

The main limitation is that data is stored in memory, meaning it is lost when the server restarts. In addition, validation and error handling are basic and could be extended further.

---

### 5. How could this system be improved?

The system could be improved by adding a database for persistent storage, improving validation and error handling, and introducing additional features such as filtering, authentication, or more advanced querying.

---

## Notes

- Data is stored in memory and resets when the server restarts
- The project is designed to demonstrate REST API development rather than production use
- The focus is on clarity, structure, and correct implementation of REST concepts  