/**
 * This exception is thrown when the user inputs a command that is not recognized by the system.
 */
package duke.exceptions;
public class InvalidInputException extends Exception {
    @Override
    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
