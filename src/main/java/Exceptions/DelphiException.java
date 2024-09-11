package Exceptions;

/**
 * The DelphiException class is a custom exception that extends the Exception class.
 * It is used to handle specific exceptions related to the UI.Delphi application.
 *
 * @author jordanchan
 */
public class DelphiException extends Exception {
    /**
     * Constructs a new DelphiException with the specified detail message.
     *
     * @param message The detail message which provides more information about the exception.
     */
    public DelphiException(String message) {
        super(message);
    }
}



