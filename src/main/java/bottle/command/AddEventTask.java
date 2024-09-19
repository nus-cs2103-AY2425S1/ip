package bottle.command;

import java.time.LocalDateTime;

import bottle.Storage;
import bottle.Ui;
import bottle.task.Event;
import bottle.task.TaskList;

/**
 * Represents a command to add an event task.
 */
public class AddEventTask extends Command {
    /**
     * The description of the task.
     */
    private final String description;

    /**
     * The starting time of the event.
     */
    private final LocalDateTime from;

    /**
     * The ending time of the event.
     */
    private final LocalDateTime to;

    /**
     * Instantiates a new AddEventTask.
     *
     * @param description the description of the event
     * @param from        the starting time of the event
     * @param to          the ending time of the event
     */
    public AddEventTask(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command to add the event task to the task list.
     *
     * @param taskList the task list to which the task will be added
     * @param ui       the user interface for displaying messages
     * @param storage   the storage for saving tasks
     * @return a message indicating the task has been added
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert !description.isEmpty() : "Task description cannot be empty!";
        assert from != null : "Starting time cannot be null!";
        assert to != null : "Ending time cannot be null!";
        if (to.isBefore(from)) {
            throw new IllegalArgumentException("End time must be after start time.");
        }

        taskList.addTask(new Event(description, from, to));
        storage.saveTasks(taskList.getTaskList());

        return ui.printTaskAddedMessage(taskList.getTaskList());
    }
}
