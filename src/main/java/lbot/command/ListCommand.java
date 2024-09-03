package lbot.command;

import lbot.exception.ExecuteCommandException;
import lbot.helper.Storage;
import lbot.helper.TaskList;
import lbot.helper.Ui;

/**
 * This class lists out all created {@link lbot.task.Task}
 */
public class ListCommand extends Command {
    /**
     * Public Constructor for ListCommand.
     */
    public ListCommand() {
    }

    /**
     * Prints out list of tasks.
     *
     * @param ui      handles user input and printing.
     * @param storage handles reading and writing to text file.
     * @param tasks   contains list of tasks.
     * @return ui method containing response.
     * @throws ExecuteCommandException thrown if method is unable to create the task.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException {
        return ui.sayList(tasks);
    }

    @Override
    public String toString() {
        return "list command ";
    }
}
