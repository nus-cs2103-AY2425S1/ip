package hana.command;

import java.util.ArrayList;
import java.util.Comparator;

import hana.storage.Storage;
import hana.task.Task;
import hana.tasklist.TaskList;
import hana.ui.Ui;

/**
 * Command to list tasks by priority.
 */
public class ListByPriorityCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> sortedTasks = new ArrayList<>(taskList.getTasks());
        sortedTasks.sort(Comparator.comparingInt(Task::getPriority));
        ui.printTasks(sortedTasks);
    }
}
