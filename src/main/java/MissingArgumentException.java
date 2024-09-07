public class MissingArgumentException extends BobException {
    public MissingArgumentException(String missingArgument) {
        super("WHOA! looks like you left something out.\n" +
                "I need the " + missingArgument + ".");
    }
}
