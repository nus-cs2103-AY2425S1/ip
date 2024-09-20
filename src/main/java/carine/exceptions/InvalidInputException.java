package carine.exceptions;

/**
 * This exception is thrown when the user inputs a command that is not recognized by the system.
 */
public class InvalidInputException extends Exception {
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("I'm sorry, but I don't know what that means\n")
                .append("You can view a list of available commands by typing: command \n");
        return stringBuilder.toString();
    }
}
