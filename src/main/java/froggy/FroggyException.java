package froggy;

/**
 * Represents an exception specific to the Froggy chatbot. This exception is thrown when
 * there is an issue with the format of the data being read from the save file
 * (e.g., an incorrectly formatted .txt file).
 */
public class FroggyException extends Exception {
    /**
     * Constructs a new FroggyException with the specified detail message.
     *
     * @param message The detail message which describes the error encountered.
     */
    public FroggyException(String message) {
        super(message);
    }
}
