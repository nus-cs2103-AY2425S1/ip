package justbot.command;

import java.time.LocalDateTime;

import justbot.task.Event;
import justbot.task.Task;
import justbot.task.TaskList;
import justbot.ui.Ui;
import justbot.storage.Storage;

public class EventCommand extends Command {
    private Event eventTask;
    public EventCommand(String descrption, LocalDateTime start, LocalDateTime end) {
        this.eventTask = new Event(descrption.trim(), start, end);
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.eventTask);
        ui.addTaskMessage(taskList, eventTask);
        storage.saveTasks(taskList);
    }
    @Override
    public Task getTask() {
        return this.eventTask;
    }
}
