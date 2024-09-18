package bruno.exceptions;

/**
 * Exception that should be thrown when task description is empty.
 */
public class EmptyTaskException extends BrunoException {
    public EmptyTaskException() {
        super("empty task?! do you think I'm stupid?! NOT COOL!");
    }
}
