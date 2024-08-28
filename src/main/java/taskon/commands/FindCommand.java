package taskon.commands;

import taskon.storage.Storage;
import taskon.task.Task;
import taskon.task.TaskList;
import taskon.ui.Ui;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private String description;

    public FindCommand(String description) {
        this.description = description;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList list = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().contains(this.description)) {
                list.addTask(task);
            }
        }
        if (list.size() == 0) {
            ui.showEmptyFind();
        } else {
            ui.listItems(list);
        }
    }
}
