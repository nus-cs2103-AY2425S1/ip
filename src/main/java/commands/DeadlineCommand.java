package commands;

import storage.Storage;
import task.Deadline;
import task.Task;
import task.TaskList;
import ui.Ui;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public Task task;

    public DeadlineCommand(String description, String date) {
        this.task = new Deadline(description, date);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        ui.showTaskAdded(task, taskList.size());
        storage.saveTasks(taskList);
    }
}
