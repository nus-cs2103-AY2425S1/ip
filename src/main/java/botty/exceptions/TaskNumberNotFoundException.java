package botty.exceptions;

/**
 * Exception thrown when task number is not found
 */
public class TaskNumberNotFoundException extends BottyException {
    /**
     * Constructs a {@code TaskNumberNotFoundException} with the given max number.
     * @param inputNumber the actual number inputted.
     * @param maxNumber the upper limit of index.
     */
    public TaskNumberNotFoundException(int inputNumber, int maxNumber) {
        super("I don't see a task with that number! Try a number from 1 to " + maxNumber);
    }
}
