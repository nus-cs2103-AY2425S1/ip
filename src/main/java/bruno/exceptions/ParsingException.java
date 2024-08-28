package bruno.exceptions;

public class ParsingException extends BrunoException {
    public ParsingException() {
        super("There was an issue parsing your command");
    }
}
