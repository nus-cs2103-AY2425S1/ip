package duke.exceptions;

public class GeneralException extends Exception {

    /**
     * Creates a duke.exceptions.GeneralException when an unknown exception happens during program execution.
     *
     * @param e description of the exception
     */
    public GeneralException(String e) {
        super(e);
    }

}
