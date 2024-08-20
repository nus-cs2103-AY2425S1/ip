public class InputEmptyException extends RuntimeException {
    private final String userInput;

    public InputEmptyException(String userInput) {
        super("You need to input something after " + userInput);
        this.userInput = userInput;
    }
}
