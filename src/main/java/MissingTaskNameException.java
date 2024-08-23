/**
 * This exception is thrown when a task is created without the necessary name.
 * <p>
 * The {@code MissingTaskNameException} is a custom exception that extends the {@code Exception} class.
 * It is used to indicate that a task is missing name.
 * </p>
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
