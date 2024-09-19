package bob.command;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * ListCommand class executes list command.
 */
public class ListCommand extends Command {

    /**
     * Constructor to initialize ListCommand
     */
    public ListCommand() {
        super();
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        String taskListString = taskList.getListRecordsString();
        ui.showList(taskListString);
        return taskListString;
    }
}
