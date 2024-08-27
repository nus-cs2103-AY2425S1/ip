package commands;

import storage.Storage;
import task.Event;
import task.Task;
import task.TaskList;
import ui.Ui;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public Task task;
    public EventCommand(String description, String from, String to) {
        this.task = new Event(description, from, to);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        ui.showTaskAdded(task, taskList.size());
        storage.saveTasks(taskList);
    }
}
