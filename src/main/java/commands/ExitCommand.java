package commands;

import bob.ExecutionStack;
import bob.Storage;
import bob.TaskList;
import bob.Ui;

/**
 * Represents a command to exit the application.
 * This command saves the current state of tasks and indicates that the application should terminate.
 */
public class ExitCommand extends Command {
    public static String[] params = new String[] {
        "bye"
    };
    public static int paramCount = 0;

    /**
     * Indicates that this command is an exit command.
     *
     * @return {@code true} because this command is used to exit the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the exit command by saving the current state of tasks.
     * This method saves the tasks to the storage and then terminates the application.
     *
     * @param tasks the {@code TaskList} that contains the current tasks.
     * @param ui the {@code Ui} to interact with the user (not used in this method).
     * @param storage the {@code Storage} where the tasks will be saved.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, ExecutionStack execStack) {
        return storage.saveFile(tasks);
    }

    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) {
        // Do nothing
        return "";
    }
}
