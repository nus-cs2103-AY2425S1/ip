package oyster.exceptions;

public class ParseException extends OysterException {
    public ParseException() {
        super(String.format("[%s] %s", "Parsing", "Your data is corrupted!"));
    }
}
