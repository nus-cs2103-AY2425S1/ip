package exception;

/**
 * A custom exception for an invalid index input which is out of bounds
 */
public class CasperBotOutOfBoundsException extends CasperBotException {
    public CasperBotOutOfBoundsException() {
        super("Index out of bounds", "Ensure that the index is within the length of the task list");
    }
}
