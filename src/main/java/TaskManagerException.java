public class TaskManagerException extends Exception {
    private final ErrorType errorType;

    public enum ErrorType {
        EMPTY_LIST,
        INVALID_MARK_TASK_NUMBER,
        INVALID_ADD_TASK_NUMBER,
        DUPLICATE_TASK,
        TASK_OUT_OF_RANGE
    }

    public TaskManagerException(String message, ErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }
//
//    public ErrorType getErrorType() {
//        return this.errorType;
//    }
}
