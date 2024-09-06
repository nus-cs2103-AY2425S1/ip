package edith.exception;

/**
 * This class extends the Exception class. It is thrown when user input is missing a keyword.
 */
public class MissingKeywordException extends Exception {
    public MissingKeywordException() {
        super("oops! you are missing a keyword. to search tasks based on keyword, please use this format: find XXX."
                + "for example:\n\n"
                + "      find book");
    }
}
