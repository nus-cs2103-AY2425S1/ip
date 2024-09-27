package gopher.exception;

/**
 * Thrown if task command has empty task description.
 */
public class EmptyTaskDescriptionException extends Exception {
    private String taskType;

    /**
     * Constructor for EmptyTaskDescriptionException class
     *
     * @param taskType type of the task that the invalid command is
     *                 try to create
     */
    public EmptyTaskDescriptionException(String taskType) {
        super();
        this.taskType = taskType;
    }

    @Override
    public String getMessage() {
        return String.format("Oops...The description of a %s cannot be empty.\nPlease try again...",
                this.taskType);
    }
}
