public class DukeException extends Exception {
    /**
     * Constructor to create a DukeException without specific message
     */
    public DukeException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Constructor to create a DukeException with provided specific message
     *
     * @param msg specific message of the exception
     */
    public DukeException(String msg) {
        super(msg);
    }

    /**
     * Method to show specific information of a DukeException
     *
     * @return exception information
     */
    @Override
    public String toString() {
        return "DukeException:" + this.getMessage();
    }
}
