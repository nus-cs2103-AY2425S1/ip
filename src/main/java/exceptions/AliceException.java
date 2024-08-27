package exceptions;

/**
 * Represents exceptions by the chatbot.
 * Can branch off into InvalidTaskException and MissingArgumentException.
 */
public class AliceException extends Exception{
    protected String message;
    public AliceException(String message) {
        this.message = message;
    }

    /**
     * Returns the string representation of the exception.
     * @return custom error message for AliceException.
     */
    @Override
    public String toString() {
        return "OOPS! I don't know what '" + this.message + "' means!";
    }
}
