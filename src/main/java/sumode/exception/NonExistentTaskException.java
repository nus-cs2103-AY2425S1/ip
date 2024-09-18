package sumode.exception;

/**
 * An Exception thrown for trying to find non-existent task.
 */
public class NonExistentTaskException extends SumoDException {

    /**
     * Constructor for NonExistentTaskException
     * @param index The index which user tried to find.
     */
    public NonExistentTaskException(int index) {
        super("Sumo cannot find task #"
                + index + ".\n"
                + "Can you check your index by checking \"list\" pleaseeeeeeeeee.....");
    }
}
