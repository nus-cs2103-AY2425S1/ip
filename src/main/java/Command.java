/**
 * The abstract Command class represents a command that Reminderebot can execute.
 */
public abstract class Command {
    /**
     * Executes the current command
     * @param tasklist
     * @param ui
     * @param storage
     * @throws ReminderebotException
     */
    public abstract void execute(TaskList tasklist, Ui ui, Storage storage) throws ReminderebotException;

    /**
     * Check if the app should exit
     * @return true if the command is bye
     */
    public abstract boolean isExit();
}
