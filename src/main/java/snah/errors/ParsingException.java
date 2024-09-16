package snah.errors;

/**
 * Class to handle the parsing exception that is thrown by the Parser
 */
public class ParsingException extends Exception {
    public ParsingException(String message) {
        super(message);
    }
}
