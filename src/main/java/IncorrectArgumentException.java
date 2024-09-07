public class IncorrectArgumentException extends BobException {
    public IncorrectArgumentException(String expectedArgument) {
        super("WHOA! That's not quite right.\n" +
                "I need " + expectedArgument + " to do this.");
    }
}
