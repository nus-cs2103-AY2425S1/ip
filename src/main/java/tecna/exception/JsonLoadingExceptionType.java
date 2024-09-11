package tecna.exception;

/**
 * Represents types of exceptions when parsing data from a json file.
 * A <code>message</code> shows the error message of each exception.
 *
 * @author DennieDan.
 */
public enum JsonLoadingExceptionType {
    INVALID_TASK_TYPE("Invalid Task type!"),
    TASKLIST_NOT_FOUND("Cannot find the \"taskList\" array in the data file!"),
    TODO_DATA_MISSING("There are missing values in the Todo task(s) in the data file!"),
    DEADLINE_DATA_MISSING("There are missing values in the Deadline task(s) in the data file!"),
    EVENT_DATA_MISSING("There are missing values in the Event task(s) in the data file!");

    public final String message;

    JsonLoadingExceptionType(String message) {
        assert message != null;

        this.message = message;
    }
}