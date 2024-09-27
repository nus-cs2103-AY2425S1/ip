package gopher.exception;

/**
 * Thrown if user gives a task number that refers to a task that does not exist in the
 * given task list
 */
public class InvalidTaskNumberException extends Exception {
    private int invalidTaskNumber;
    public InvalidTaskNumberException(int taskNumber) {
        this.invalidTaskNumber = taskNumber;
    }

    @Override
    public String getMessage() {
        return String.format("Sorry, I can't seem to find a valid task with task number %s...\n"
                + "Please try again with a valid task number...", this.invalidTaskNumber);
    }
}
