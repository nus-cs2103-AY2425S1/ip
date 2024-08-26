package simon;
/**
 * Represents a command in the Simon application.
 * Each command implements this interface to define its own execution behavior.
 */
public interface Command {
    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param taskList the list of tasks to be manipulated by the command
     * @param ui the user interface to interact with the user
     * @param storage the storage to save or retrieve data
     */
    public void execute(TaskList taskList, Ui ui, Storage storage);
}