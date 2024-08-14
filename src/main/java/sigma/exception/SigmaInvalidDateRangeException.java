package sigma.exception;

public class SigmaInvalidDateRangeException extends SigmaException {
    @Override
    public String toString() {
        return String.format("%s End date cannot be earlier than start date.", super.toString());
    }

}
