package carly.exception;

/**
 * Represents a general exception for the Carly application.
 * This class is used as a base exception class for custom exceptions in the Carly application.
 * It extends the built-in Exception class and provides a constructor for setting an error message.
 */
public class CarlyException extends Exception{

    /** Constructs a new CarlyException with the specified detail message. */
    public CarlyException(String message) {
        super(message);
    }
}
