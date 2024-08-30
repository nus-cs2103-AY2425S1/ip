package tecna;

public enum JsonLoadingExceptionType {
    INVALID_TASK_TYPE("Invalid Task type!"),
    TASKLIST_NOT_FOUND("Cannot find the \"taskList\" array in the data file!"),
    TODO_DATA_MISSING("There are missing values in the Todo task(s) in the data file!"),
    DEADLINE_DATA_MISSING("There are missing values in the Deadline task(s) in the data file!"),
    EVENT_DATA_MISSING("There are missing values in the Event task(s) in the data file!");

    public final String message;

    JsonLoadingExceptionType(String message) {
        this.message = message;
    }
}