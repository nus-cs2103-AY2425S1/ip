package exception;

public class SkibidiSigmaInvalidTaskException extends SkibidiSigmaException {
    private int taskNumber;
    public SkibidiSigmaInvalidTaskException(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String toString() {
        return String.format("%s Task number %d is invalid.", super.toString(), this.taskNumber);
    }
}
