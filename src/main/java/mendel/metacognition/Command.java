package mendel.metacognition;

/**
 * Provides a base structure for different types of command type of varying parameter lengths that can be executed.
 */
public abstract class Command {
    /**
     * Executes the command and returns a corresponding message.
     *
     * @return A string message associated with the command's execution.
     */
    public abstract String speak();
}
