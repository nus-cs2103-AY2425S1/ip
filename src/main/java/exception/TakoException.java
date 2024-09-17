package exception;

/**
 * Represents an exception that is unique to the Tako chatbot.
 */
public class TakoException extends Exception {

    private String errorType;
    private String errorMessage;

    /**
     * Creates an exception with the type of error.
     *
     * @param errorType the type of error
     */
    public TakoException(String errorType) {
        this.errorType = errorType;
    }

    /**
     * Returns the error message that is input to the constructor
     * of TakoException
     *
     * @return error message
     */
    public String message() {
        switch (errorType) {
        case "command":
            errorMessage = "Tako does not understand this command!";
            break;
        case "priority":
            errorMessage = "Wrong format! priority command should have the form 'priority x y',"
                           + " where x is 'low' or 'medium' or 'high' and y is an integer";
            break;
        case "find":
            errorMessage = "Wrong format! Find command should have the form 'find x', "
                           + "where x is not empty";
            break;
        case "mark":
            errorMessage = "Wrong format! mark command should have the form 'mark x',"
                           + " where x is an integer";
            break;
        case "unmark":
            errorMessage = "Wrong format! Unmark command should have the form 'unmark x',"
                           + " where x is an integer";
            break;
        case "delete":
            errorMessage = "Wrong format! Delete command should have the form 'delete x',"
                           + " where x is an integer";
            break;
        case "todo":
            errorMessage = "Wrong format! Todo command should have the form 'todo x',"
                           + " where x is not empty";
            break;
        case "deadline":
            errorMessage = "Wrong format! Deadline command should have the form "
                           + "'deadline x /by YYYY-MM-DD', where x is not empty";
            break;
        case "event":
            errorMessage = "Wrong format! Event command should have the form"
                           + " 'event x /from YYYY-MM-DD /to YYYY-MM-DD', where x is not empty";
            break;
        }
        return errorMessage;
    }
}
