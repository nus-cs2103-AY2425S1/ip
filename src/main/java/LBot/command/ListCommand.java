package LBot.command;

import LBot.exception.ExecuteCommandException;
import LBot.helper.Storage;
import LBot.helper.TaskList;
import LBot.helper.Ui;

/**
 * This class lists out all created {@link LBot.task.Task}
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
     * @throws ExecuteCommandException thrown if method is unable to create the task.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException {
        ui.printTaskList(tasks);
    }

    @Override
    public String toString() {
        return "list command ";
    }
}
