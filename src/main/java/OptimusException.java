// Let ChatGPT check and suggest comments and JavaDocs according to CS2103T style guide
/**
 * Custom exception class for handling errors specific to the Optimus task management system.
 */
public class OptimusException extends Exception {
    /**
     * Constructs an OptimusException with the specified detail message.
     *
     * @param msg The detail message for the exception.
     */
    public OptimusException(String msg) {
        super(msg);
    }
}
