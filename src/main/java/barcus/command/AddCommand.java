package barcus.command;

/**
 * Abstract command to add in a task
 */
public abstract class AddCommand extends Command {
    protected String description;

    /**
     * Constructor
     * @param description String
     */
    public AddCommand(String description) {
        this.description = description;
    }
}
