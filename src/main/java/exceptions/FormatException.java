package exceptions;

/**
 * Represents formatting exceptions faced by the chatbot.
 * Example when a number is expected but the user inputs a string.
 */
public class FormatException extends AliceException {
    protected String input;

    /**
     * Initialises a FormatException object.
     *
     * @param input the invalid input.
     */
    public FormatException(String input) {
        super(input);
        this.input = input;
    }

    /**
     * Returns the string representation of the exception.
     *
     * @return custom error message for FormatException.
     */
    @Override
    public String toString() {
        return input + " is not a number!";
    }
}
