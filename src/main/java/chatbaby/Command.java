package chatbaby;

/**
 * Represents an abstract command in the ChatBaby application.
 * Specific command types (e.g., AddCommand, DeleteCommand) extend this class
 * and implement the execute method to define the command's behavior.
 */
public abstract class Command {
    protected String commandBody;

    /**
     * Constructs a Command with the given command body.
     *
     * @param commandBody The body of the command entered by the user.
     */
    public Command(String commandBody) {
        this.commandBody = commandBody;
    }

    /**
     * Executes the command with the given TaskList, Ui, and Storage.
     *
     * @param tasks   The TaskList containing the user's tasks.
     * @param ui      The Ui responsible for interacting with the user.
     * @param storage The Storage responsible for loading and saving tasks.
     * @throws ChatBabyException If the command encounters an error during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBabyException;

    /**
     * Returns whether this command is an exit command.
     *
     * @return false by default. Override in subclasses for exit commands.
     */
    public boolean isExit() {
        return false;
    }
}
