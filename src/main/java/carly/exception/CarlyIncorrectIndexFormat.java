package carly.exception;

/**
 * Exception thrown when an index provided is not in the correct numeric format.
 */
public class CarlyIncorrectIndexFormat extends CarlyException {

    /**
     * Constructs a new CarlyIncorrectIndexFormat exception with a predefined error message.
     * The message indicates that the index should be a number and prompts the user to reenter their input.
     */
    public CarlyIncorrectIndexFormat() {
        super("Oh no!, index should be a number. Please reenter your message.");
    }
}