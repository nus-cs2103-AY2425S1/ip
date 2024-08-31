package myapp.command;

import myapp.core.Storage;
import myapp.task.Event;
import myapp.task.Task;
import myapp.task.TaskList;

import java.time.LocalDateTime;

/**
 * Represents a command that adds a new {@link Event} task to the task list.
 * This command stores the description, start time, and end time of the event, and
 * adds the task to the list when executed.
 */
public class EventCommand extends AddCommand {

    /** The start date and time of the event. */
    private LocalDateTime from;

    /** The end date and time of the event. */
    private LocalDateTime to;

    /**
     * Constructs an EventCommand with the specified task description, start time, and end time.
     *
     * @param s    The description of the event task.
     * @param from The start date and time of the event.
     * @param to   The end date and time of the event.
     */
    public EventCommand(String s, LocalDateTime from, LocalDateTime to) {
        super(s);
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command by adding a new {@link Event} task to the task list.
     * The task is then saved to storage, and a confirmation message is returned.
     *
     * @param tasks   The task list to which the event task will be added.
     * @param storage The storage system used to save the task list.
     * @return A string message confirming the addition of the event task to the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task task = new Event(description, from, to);
        tasks.add(task);
        saveTasks(tasks, storage);
        return printAddMessage(tasks, task);
    }
}
