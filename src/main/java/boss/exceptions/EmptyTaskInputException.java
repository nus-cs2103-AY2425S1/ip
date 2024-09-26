package boss.exceptions;

import boss.exceptions.BossException;

/**
 * Represents an EmptyTaskInputException when the user does not
 * specify which task to delete, mark or unmark.
 */
public class EmptyTaskInputException extends BossException {

    private final String operation;

    /**
     * Constructs an EmptyTaskInputException with an operation
     *
     * @param operation specifies the operation
     */
    public EmptyTaskInputException(String operation) {
        super(operation);
        this.operation = operation;
    }

    @Override
    public String toString() {
        if (operation.equals("mark")) {
            return "Please specify a task number to mark";
        } else if (operation.equals("unmark")) {
            return "Please specify a task number to unmark";
        } else if (operation.equals("delete")) {
            return "My homie, please specify a task number to delete";
        }
        return "Invalid Empty Task Input bro";
    }
}
