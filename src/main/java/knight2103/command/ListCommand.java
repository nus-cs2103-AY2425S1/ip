package knight2103.command;

import knight2103.Ui;
import knight2103.files.Storage;
import knight2103.tasks.TaskList;

/**
 * Models after a command that shows a full list of task.
 */
public class ListCommand extends FormatListCommand {
    ListCommand() {
        super(CommandVerb.LIST);
    }

    /**
     * Executes the ListCommand which lists out all the tasks stored in the list of tasks.
     *
     * @param tasks The object storing the list of tasks found in the bot.
     * @param ui The user interface of the bot.
     * @param storage The object containing the file that saves the list of tasks.
     * @return The list of tasks stored after command execution in the bot's GUI.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String taskListInString = formatToList(tasks);
        return ui.showList(taskListInString);
    }
}
