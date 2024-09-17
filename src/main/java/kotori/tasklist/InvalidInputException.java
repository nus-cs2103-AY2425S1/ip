package kotori.tasklist;

/**
 * This class in thrown wen the command is not readable.
 * */

public class InvalidInputException extends Exception {
    public InvalidInputException() {
        super();
    }

    /**
     * returns the error message.
     *
     * @return the error message.
     * */
    public String getMessage() {
        return "Sorry~ I can not understand what you said just now.";
    }

}
