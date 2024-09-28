package oyster.exceptions;

/**
 * Exception when parsing of storage files go wrong.
 */
public class ParseException extends OysterException {
    public ParseException() {
        super(String.format("[%s] %s", "Parsing", "Your data is corrupted!"));
    }
}
