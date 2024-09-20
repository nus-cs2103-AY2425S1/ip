package commands;

import bob.ExecutionStack;
import bob.Storage;
import bob.TaskList;
import bob.Ui;

/**
 * Represents a command that undoes the most recent command from the execution stack.
 */
public class UndoCommand extends Command {
    public static String[] params = new String[] {
        "undo"
    };
    public static int paramCount = 0;

    /**
     * Executes the undo operation by popping the most recent command from the execution stack
     * and calling its {@code undo} method. If the execution stack is empty, returns a message
     * indicating that there are no commands to undo.
     *
     * @param tasks the list of tasks.
     * @param ui the UI for user interaction.
     * @param storage the storage handler.
     * @param execStack the execution stack for managing command history.
     * @return the result of the undo operation, or a message if no commands are available to undo.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, ExecutionStack execStack) {
        if (execStack.isEmpty()) {
            return "No commands to undo.";
        }

        Command commandToUndo = execStack.pop();
        return commandToUndo.undo(tasks, ui, storage);
    }

    /**
     * The undo operation for the undo command does nothing, as the undo command itself cannot be undone.
     *
     * @param tasks the list of tasks.
     * @param ui the UI for user interaction.
     * @param storage the storage handler.
     * @return an empty string, as no action is performed.
     */
    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) {
        // Do nothing
        return "";
    }
}
