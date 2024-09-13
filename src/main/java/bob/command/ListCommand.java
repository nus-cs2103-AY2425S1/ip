package bob.command;

import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * ListCommand class executes list command.
 */
public class ListCommand extends Command {

    /**
     * Constructor to initalise ListCommand
     */
    public ListCommand() {
        super();
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        String allRecords = "Here are the tasks in your list:";
        for (int i = 1; i <= taskList.getRecordSize(); i++) {
            Task currTask = taskList.getIndexedTask(i);
            allRecords += "\n\t" + i + "." + currTask.getTaskListItem();
        }
        ui.showList(allRecords);
        //storage.saveRecordsToStorage(taskList.getAllRecords());
        return allRecords;
    }
}
