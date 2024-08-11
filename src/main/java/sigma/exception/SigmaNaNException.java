package sigma.exception;

public class SigmaNaNException extends SigmaException {
    @Override
    public String toString() {
        return String.format("%s sigma.task.Task number is not numeric. Please enter a number", super.toString());
    }
}
