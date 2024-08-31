package miku.exception;

/**
 * Thrown to indicate the command entered is invalid.
 */
public class InvalidCommandException extends Exception {
    String desc = "";

    /**
     * Initialises an InvalidCommandException.
     * @param desc the message to be added to the end of the warning.
     */
    public InvalidCommandException(String desc) {
        this.desc = desc;
    }

    /**
     * prints the error with a description.
     */
    public void print() {
        System.out.println("The command is not valid: " + desc);
    }
}
