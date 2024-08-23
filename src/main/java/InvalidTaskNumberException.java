public class InvalidTaskNumberException extends YapperException {
    public InvalidTaskNumberException(int taskNumber) {
        super("Hello boss your task number " + (taskNumber + 1) + " is not valid. Please provide a valid task number.");
    }
}
