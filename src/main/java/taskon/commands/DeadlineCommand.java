package taskon.commands;

import taskon.storage.Storage;
import taskon.task.Deadline;
import taskon.task.Task;
import taskon.task.TaskList;
import taskon.ui.Ui;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private Task task;

    public DeadlineCommand(String description, String date) {
        this.task = new Deadline(description, date);
    }

    public Task getTask() {
        return task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        ui.showTaskAdded(task, taskList.size());
        storage.saveTasks(taskList);
    }
}
