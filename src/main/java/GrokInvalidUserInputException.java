public class GrokInvalidUserInputException extends Exception {
    public GrokInvalidUserInputException() {
        super("Invalid user input");
    }

    public GrokInvalidUserInputException(String msg) {
        super(msg);
    }
}
