package duke;

/**
 * The {@code MentalHealthException} class represents an exception specific to the
 * MentalHealth chatbot application. This exception is thrown when the application
 * encounters an error related to the mental health functionalities or user interactions.
 * <p>
 * This class extends {@code Exception}, thereby providing all the functionalities of a standard
 * exception, while allowing for more specific error handling within the MentalHealth application.
 * </p>
 */
public class MentalHealthException extends Exception {

    /**
     * Constructs a new {@code MentalHealthException} with the specified detail message.
     * The message can be retrieved later by the {@link #getMessage()} method.
     *
     * @param message The detail message, which provides more information about the cause of the exception.
     */
    public MentalHealthException(String message) {
        super(message);
    }
}


