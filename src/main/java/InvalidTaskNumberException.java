public class InvalidTaskNumberException extends YapperException {
    public InvalidTaskNumberException(int taskNumber) {
        super("Task number " + (taskNumber + 1) + " is not valid. Please provide a valid task number.");
    }
}
