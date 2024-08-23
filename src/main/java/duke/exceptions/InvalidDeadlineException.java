package duke.exceptions;

public class InvalidDeadlineException extends Exception {
    /**
     * Creates a duke.exceptions.InvalidDeadline when the user enters a deadline that is separated by more than 1 space.
     * examples of valid deadlines:
     * 1. 2/12/2019 1800
     * 2. 2/12/2019
     *
     * @param e: description of the exception
     */
    public InvalidDeadlineException(String e) {
        super(e);
    }
}