public class EmptyDescriptionException extends CommandFoundButInvalidException {
    public EmptyDescriptionException(String input) {
        super(String.format("Bruh, you can't just type %s. Give me more details.", input));
    }
}
