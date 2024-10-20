package darwin.command;

import darwin.task.TaskManager;
import darwin.ui.Ui;

/**
 * ListCommand class to represent a command to list all tasks.
 */
public class ListCommand extends Command {
    public static final String CMD_WORD = "list";
    private static final String CHECK_LIST_STR = "Here are the tasks in your list:";

    /**
     * Executes the list command.
     * @param taskManager task manager to execute the command
     * @param ui user interface to display messages
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        String msg = ListCommand.CHECK_LIST_STR + "\n" + taskManager.getTaskListStr();
        ui.send(msg);
    }
}
