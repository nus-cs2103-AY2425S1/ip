package star.exception;

/**
 * Represents exceptions that are specific to the Star chatbot.
 * Inherits from the Exception class.
 */
public class StarException extends Exception {

    /**
     * Constructs a StarException with the given message.
     * @param message which is the String message to be displayed when the particular Exception occurs.
     */
    public StarException(String message) {
        super(message);
    }

    /**
     * Throws an exception when the description of the ToDo task is empty.
     * @return a new StarException with the given message.
     */
    public static StarException emptyTodo() {
        return new StarException("oopsie! a todo's description can't be empty.");
    }

    /**
     * Throws an exception when the description of the Event task is empty.
     * @return a new StarException with the given message.
     */
    public static StarException emptyEvent() {
        return new StarException("oopsie! an event's description can't be empty.");
    }

    /**
     * Throws an exception when the description of the Deadline task is empty.
     * @return a new StarException with the given message.
     */
    public static StarException emptyDeadline() {
        return new StarException("oopsie! a deadline's description can't be empty.");
    }

    /**
     * Throws an exception when the command is unknown.
     * @return a new StarException with the given message.
     */
    public static StarException unknownCommand() {
        return new StarException("oopsie! I don't know what that means :(");
    }
}
