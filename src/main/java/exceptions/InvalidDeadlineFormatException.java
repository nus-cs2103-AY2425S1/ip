package exceptions;

/**
 * Thrown when an invalid deadline string is parse by Him.
 *
 * @author IsaacPangTH
 */
public class InvalidDeadlineFormatException extends HimException {

    /**
     * Constructor for<code>InvalidDeadlineFormatException</code>.
     */
    public InvalidDeadlineFormatException() {
        super("Deadlines need a description and a due date!\n" +
                "Use the format: \"deadline [description] /by [due date] /at [due time]\"\n" +
                "Note: due times are optional!");
    }
}
