package papadom.Exceptions;
/**
 * Exception thrown when the input format for a task is incorrect.
 */
public class IncorrectTaskInputFormatException extends Exception {
    /**
     * Constructs an IncorrectTaskInputFormatException with a predefined message
     * guiding the user on the correct input format.
     */
    public IncorrectTaskInputFormatException () {
        super(" Please enter the correct format!\n" +
                "  For todo tasks: todo [task]\n" +
                "  For deadline tasks: deadline [task] /by yyyy-mm-dd OR yyyy-mm-dd hh-mm\n" +
                "  For event tasks: event [task] /from [date & time] /to [date & time]");
    }
}
