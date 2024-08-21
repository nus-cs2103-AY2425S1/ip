public class InvalidSyntaxException extends CommandFoundButInvalidException {
    public InvalidSyntaxException(String input) {
        super(String.format("Uh Oh, wrong syntax for the command - %s", input));
    }
}
