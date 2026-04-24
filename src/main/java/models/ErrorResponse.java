package models;

import java.time.LocalDateTime;

/**
 * Standard structure for API error responses.
 */
public class ErrorResponse {

    // HTTP status code (e.g. 404, 400, etc.)
    public int status;

    // short error message
    public String message;

    // more detailed explanation
    public String details;

    // time the error occurred
    public LocalDateTime timestamp = LocalDateTime.now();

    public ErrorResponse(int status, String message, String details) {
        this.status = status;
        this.message = message;
        this.details = details;
    }
}