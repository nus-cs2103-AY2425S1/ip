package carly.exception;

/**
 * Exception thrown when a task description is missing in a command input.
 */
public class CarlyNoTaskDescription extends CarlyException {

    /**
     * Constructs a new CarlyNoTaskDescription exception with a detailed error message.
     * The message informs the user that they have forgotten to include a task description and
     * prompts them to reenter their command.
     */
    public CarlyNoTaskDescription() {
        super("Oops. You forgot to enter a task description. Please reenter your message.");
    }

}
