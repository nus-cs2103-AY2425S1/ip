package cloud.util;

/**
 * Represents a response from Cloud, including a message and status.
 */
public class CloudResponse {
    private final String message;
    private final ResponseStatus responseStatus;

    /**
     * Constructs a CloudResponse with the given message and status.
     *
     * @param message         The response message
     * @param responseStatus  The status of the response
     */
    public CloudResponse(String message, ResponseStatus responseStatus) {
        this.message = message;
        this.responseStatus = responseStatus;
    }

    /**
     * Returns the message of the response.
     *
     * @return The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the status of the response.
     *
     * @return The response status
     */
    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }
}
