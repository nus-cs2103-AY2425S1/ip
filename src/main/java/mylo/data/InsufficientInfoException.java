package mylo.data;

import mylo.task.TaskType;

/**
 * Exception thrown when there is insufficient information to create a task.
 * <p></p>
 * <p>This exception is used to indicate that the user has not provided all
 * the necessary information required to create a task of a specific type.</p>
 *
 * @author cweijin
 */
public class InsufficientInfoException extends Exception {

    /**
     * Constructs an {@code InsufficientInfoException} with a message based on the provided task type.
     *
     * @param type The type of task that requires more information.
     */
    public InsufficientInfoException(TaskType type) {
        super(
            switch (type) {
            case TODO -> "Oops. task.Task title is required. Please try again with: todo <Task Title>.";
            case EVENT -> "Oops. Seems like we are missing some information. Please try again with: "
                            + "event <Task Title> /from <dd-mm-yyyy hhmm> /to <dd-mm-yyyy hhmm>";
            case DEADLINE -> "Oops. Seems like we are missing some information. Please try again with: "
                            + "deadline <Task Title> /by <dd-mm-yyyy hhmm>.";
            }
        );
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
