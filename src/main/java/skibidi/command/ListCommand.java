package skibidi.command;

import skibidi.Storage;
import skibidi.TaskList;
import skibidi.Ui;

/** Command to list all items in current task list. */
public class ListCommand extends AbstractCommand {
    /** Execute list command and return string representing items to be printed. */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        if (taskList.isEmpty()) {
            return "NO ITEMS";
        }
        return "LISTING ITEMS:\n" + taskList.toString();
    }
}
