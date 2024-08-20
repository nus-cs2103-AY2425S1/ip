public class InputEmptyException extends RuntimeException {

    public InputEmptyException(String userInput) {
        super("You need to input something after " + userInput);
    }

    public InputEmptyException(String userInput, String missingInfo) {
        super("You need to include " + missingInfo + " in your command!");
    }
}
