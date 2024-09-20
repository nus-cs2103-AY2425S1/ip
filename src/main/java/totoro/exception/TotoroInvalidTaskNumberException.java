package totoro.exception;

public class TotoroInvalidTaskNumberException extends TotoroException {
    private int taskNumber;

    public TotoroInvalidTaskNumberException(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String toString() {
        return String.format("%s Task number %d is invalid", super.toString(), this.taskNumber + 1);
    }
}
