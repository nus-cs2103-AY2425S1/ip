public class InvalidTaskException extends AliceException{
    protected int taskNumber;
    public InvalidTaskException(String message, int taskNumber) {
        super(message);
        this.taskNumber = taskNumber;
    }

    @Override
    public String toString() {
        return String.format("Task %d does not exist!", this.taskNumber);
    }
}
