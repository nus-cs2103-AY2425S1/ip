package edith.exception;

/**
 * This class extends Exception class. It is thrown when the user does not input a deadline after naming the task.
 */
public class MissingDeadlineException extends Exception {
    public MissingDeadlineException() {
        super(" oops! deadline cannot be empty! please enter a deadline after task type and task name. " +
                "for example:\n\n" +
                "      deadline cs2100 quiz /by 2pm");
    }
}
