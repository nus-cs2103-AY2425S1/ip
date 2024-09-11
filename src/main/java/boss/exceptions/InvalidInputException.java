package boss.exceptions;

import boss.exceptions.BossException;

/**
 * Represents an exception thrown when the user's input is
 * invalid when creating a task.
 */
public class InvalidInputException extends BossException {
    private final String type;

    /**
     * Constructs an InvalidInputException with a type.
     *
     * @param type specifies the type of task
     */
    public InvalidInputException(String type) {
        super(type);
        this.type = type;
    }

    @Override
    public String toString() {
        String str = "Invalid Input! ";
        if (type.equals("todo")) {
            return str + "The description of todo cannot be empty!";
        } else if (type.equals("deadline")) {
            return str + "Please specify a deadline date!";
        } else if (type.equals("event")) {
            return str + "You must specify a description, start and end date for an event!";
        }
        return str;
    }
}
