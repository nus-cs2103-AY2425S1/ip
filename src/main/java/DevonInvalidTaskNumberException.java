public class DevonInvalidTaskNumberException extends DevonException {
    private final int taskIndex;

    public DevonInvalidTaskNumberException(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Task %d not found", taskIndex);
    }
}
