package sigma.exception;

public class SigmaInvalidTaskException extends SigmaException {
    private int taskNumber;
    public SigmaInvalidTaskException(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String toString() {
        return String.format("%s sigma.task.Task number %d is invalid.", super.toString(), this.taskNumber);
    }
}
