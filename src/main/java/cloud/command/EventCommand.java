package cloud.command;

import cloud.task.Event;
import cloud.util.DateTime;
import cloud.util.Storage;
import cloud.util.TaskList;
import cloud.util.Ui;

/**
 * Represents a command to add an event task. An <code>EventCommand</code>
 * object corresponds to a task with a description, start date, and end date.
 */
public class EventCommand extends Command {
    private final String description;
    private final DateTime startDate;
    private final DateTime endDate;

    public EventCommand(String description, DateTime startDate, DateTime endDate) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(this.description, this.startDate, this.endDate);
        tasks.add(event);
        storage.saveData(tasks);
        return ui.showAddedTask(tasks);
    }
}
