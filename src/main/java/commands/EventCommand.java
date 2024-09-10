package commands;

import java.time.LocalDateTime;

import exceptions.DownyException;
import storage.Storage;
import tasks.Event;
import tasks.TaskList;
import ui.Ui;

/**
 * The {@code EventCommand} class represents a command that, when executed, adds a new event task
 * to the task list. The event includes a description, a start time, and an end time.
 */
public class EventCommand implements Command {

    private final String taskDescription;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Constructs a new {@code EventCommand} with the specified task description, start time,
     * and end time.
     *
     * @param taskDescription The description of the event task to be added.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     */
    public EventCommand(String taskDescription, LocalDateTime startTime, LocalDateTime endTime) {
        assert taskDescription != null : "Task description cannot be null.";
        assert startTime != null : "Task startTime cannot be null.";
        assert endTime != null : "Task endTime cannot be null.";

        this.taskDescription = taskDescription;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Executes the Event command, adding a new event task to the task list, saving it to storage,
     * and displaying the added task via the user interface.
     *
     * @param storage The storage handler used for saving the new event task.
     * @param tasks   The list of tasks currently in memory.
     * @param ui      The UI handler used for interacting with the user.
     * @throws DownyException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws DownyException {
        Event e = tasks.addEvent(this.taskDescription, this.startTime, this.endTime);
        storage.writeEventToFile(e);
        return ui.displayTaskAdded(e, tasks.getSize());
    }

    /**
     * Returns the description of the event task associated with this command.
     *
     * @return The event task description.
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }

    /**
     * Returns the start time of the event associated with this command.
     *
     * @return The start time of the event.
     */
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    /**
     * Returns the end time of the event associated with this command.
     *
     * @return The end time of the event.
     */
    public LocalDateTime getEndTime() {
        return this.endTime;
    }
}
