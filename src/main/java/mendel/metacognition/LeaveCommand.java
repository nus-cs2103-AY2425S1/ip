package mendel.metacognition;

/**
 * Represents a command to exit the application.
 * Provides a farewell message when executed.
 */
public class LeaveCommand extends Command {
    /**
     * Executes the leave command and returns a farewell message.
     *
     * @return Prints a string message saying goodbye to the user.
     */
    public String speak() {
        return this.toString();
    }

    /**
     * Returns a string representation of the leave command message.
     *
     * @return A string message saying goodbye to the user.
     */
    @Override
    public String toString() {
        return "Bye. Hope to see you again soon!";
    }
}
