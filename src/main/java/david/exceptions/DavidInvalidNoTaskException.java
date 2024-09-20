package david.exceptions;

/**
 * Invalid task exception class for David class
 */
public class DavidInvalidNoTaskException extends DavidException {

    public DavidInvalidNoTaskException() {}

    @Override
    public String toString() {
        return "You do not have any tasks for now!";
    }
}
