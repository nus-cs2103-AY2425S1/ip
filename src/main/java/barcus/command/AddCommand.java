package barcus.command;

/**
 * Abstract command to add in a task
 */
public abstract class AddCommand extends Command {
    /**
     * The description of the task to be added.
     */
    protected String description;

    /**
     * Constructs an AddCommand with the specified task description.
     *
     * @param description the description of the task to be added
     */
    public AddCommand(String description) {
        this.description = description;
    }
}
