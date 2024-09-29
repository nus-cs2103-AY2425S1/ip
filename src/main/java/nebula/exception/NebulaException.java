package nebula.exception;

public class NebulaException extends Exception {
    /**
     * Constructs a NebulaException with the specified error message
     *
     * @param error The String description of the error message
     */
    public NebulaException(String error) {
        super(error);
    }
}
