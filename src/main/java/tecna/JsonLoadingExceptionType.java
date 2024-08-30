package tecna;

public enum JsonLoadingExceptionType {
    INVALID_TASK_TYPE("Oops! Invalid Task type!"),
    TASKLIST_NOT_FOUND("Oops! Cannot find the \"taskList\" array in the data file!");

    public final String message;

    JsonLoadingExceptionType(String message) {
        this.message = message;
    }
}