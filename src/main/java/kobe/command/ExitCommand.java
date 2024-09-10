package kobe.command;

import kobe.util.Storage;
import kobe.task.TaskList;
import kobe.util.Ui;

/**
 * Represents a command to exit the Duke chatbot application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, which displays a goodbye message to the user.
     *
     * @param tasks   The TaskList object containing all tasks (not used in this command).
     * @param ui      The Ui object responsible for user interactions.
     * @param storage The Storage object responsible for saving and loading tasks (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.setResponse("Goodbye! My man.");
    }

    /**
     * Indicates that this command is an exit command.
     *
     * @return {@code true} since this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
