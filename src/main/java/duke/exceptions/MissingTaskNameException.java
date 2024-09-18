package duke.exceptions;

/**
 * This exception is thrown when a task is created without the necessary name.
 */
public class MissingTaskNameException extends Exception {
    private String taskType;

    public MissingTaskNameException(String taskType) {
        this.taskType = taskType;
    }
    @Override
    public String toString() {
        return "OOPS!!! The description of a " + taskType + " cannot be empty.";
    }
}
