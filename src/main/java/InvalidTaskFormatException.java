public class InvalidTaskFormatException extends Exception {
    public InvalidTaskFormatException(String taskType) {
        super("Invalid format for " + taskType + " task.");
    }
}