package sumode.exception;

/**
 * Thrown when user input an event where start time is after end time
 */
public class EndBeforeStartException extends Exception {

    /**
    * Constructor for EndBeforeStartException Exception
    */
    public EndBeforeStartException() {
        super("Legend. You end a task before you even start! Teach me senpai!");
    }
}
