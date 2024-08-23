/**
 * This exception is thrown when the user inputs a command that is not recognized by the system.
 * <p>
 * The {@code InvalidInputException} is a custom exception that extends the {@code Exception} class.
 * It is used to indicate that the user's input could not be interpreted as a valid command.
 * </p>
 */
public class InvalidInputException extends Exception {
    @Override
    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
