package justbot.command;

import java.time.LocalDateTime;

import justbot.storage.Storage;
import justbot.task.Event;
import justbot.task.Task;
import justbot.task.TaskList;
import justbot.ui.Ui;

/**
 * Represents a command to add an Event task to the task list in the Justbot application.
 * The EventCommand creates a new Event task with a specified description, start time, and end time.
 */
public class EventCommand extends Command {
    private Event eventTask;

    /**
     * Constructs an EventCommand with the specified task description, start time, and end time.
     *
     * @param description The description of the Event task.
     * @param start The start date and time of the event.
     * @param end The end date and time of the event.
     */
    public EventCommand(String description, LocalDateTime start, LocalDateTime end) {
        this.eventTask = new Event(description.trim(), start, end);
    }

    /**
     * Executes the command to add the Event task to the task list.
     * The task is added to the task list, a confirmation message is displayed, and the task list is saved to storage.
     *
     * @param taskList The list of tasks to which the Event task is added.
     * @param ui The UI instance used to display messages to the user.
     * @param storage The Storage instance used to save the updated task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.eventTask);
        taskList.sortTasksChronologically();
        storage.saveTasks(taskList);
        return ui.addTaskMessage(taskList, eventTask);
    }

    /**
     * Returns the Event task associated with this command.
     *
     * @return The Event task created by this command.
     */
    @Override
    public Task getTask() {
        return this.eventTask;
    }
}
