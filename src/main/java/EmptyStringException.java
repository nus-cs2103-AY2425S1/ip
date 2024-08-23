public class EmptyStringException extends Exception {
    public EmptyStringException() {
        super("You did not type anything");
    }
}
