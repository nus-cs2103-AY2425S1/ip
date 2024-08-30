package thebotfather.command;

import thebotfather.util.Storage;
import thebotfather.util.TaskList;
import thebotfather.util.TheBotFatherException;
import thebotfather.util.Ui;

/**
 * Represents a command to print the current task list.
 */
public class PrintTaskListCommand extends Command {

    /**
     * Executes the print task list command by retrieving the task descriptions
     * from the task list and printing them using the user interface.
     *
     * @param taskList The task list containing the tasks to be printed.
     * @param ui The user interface used to display the task list.
     * @param storage The storage system (not used in this command).
     * @throws TheBotFatherException If an error occurs while retrieving the task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException {
        ui.printTaskList(taskList.getListDesc());
    }
}
