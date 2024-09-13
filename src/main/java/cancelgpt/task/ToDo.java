package cancelgpt.task;

import java.time.format.DateTimeParseException;

/**
 * The representation of ToDo task.
 */
public class ToDo extends Task {

    /**
     * Initialises ToDo with a description, with initial status
     * of not done.
     *
     * @param description the description of the todo
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Initialises ToDo with a an initial status set to isDone,
     * description, with initial status
     *
     * @param description the description of the todo
     */
    public ToDo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Returns a ToDo task generated from information from savedDataArr array.
     *
     * @param savedDataArr the array containing information to generate the ToDo task
     * @return the ToDo task created
     * @throws DateTimeParseException if the local date time in the savedDataArr cannot be parsed
     */
    public static Task getTaskFromSavedDataStringArr(String[] savedDataArr) {
        return new ToDo(getStatusBoolean(Integer.parseInt(savedDataArr[1])), savedDataArr[2]);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getSavedDataString() {
        return "T" + " | " + super.getSavedDataString();
    }
}
