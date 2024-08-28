package taskon.commands;

import taskon.storage.Storage;
import taskon.task.Event;
import taskon.task.Task;
import taskon.task.TaskList;
import taskon.ui.Ui;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private Task task;
    public EventCommand(String description, String from, String to) {
        this.task = new Event(description, from, to);
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
