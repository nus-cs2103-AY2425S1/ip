public class InputEmptyException extends RuntimeException {

    public InputEmptyException(String userInput) {
        super("You need to input something after " + userInput);
    }
}
