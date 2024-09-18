package hamyo.misc;

/**
 * Represents a throwable for when the Hamyo chatbot encounters issues, such as
 * incorrect user input.
 *
 * @author Han Yu
 */
public class HamyoException extends Exception {

    /**
     * Constructor for HamyoException instance. Created when Hamyo chatbot
     * encounters an issue.
     *
     * @param message Message to inform user of the issue.
     */
    public HamyoException(String message) {
        super(message);
    }

    /**
     * Converts the HamyoException to a String representation.
     *
     * @return Formatted String to represent the HamyoRepresentation.
     */
    @Override
    public String toString() {
        return "Oh No! " + this.getMessage();
    }
}
