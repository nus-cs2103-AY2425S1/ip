package jeff.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import jeff.Storage;
import jeff.TaskList;
import jeff.Ui;
import jeff.exceptions.JeffException;
import jeff.task.Deadline;
import jeff.task.Event;
import jeff.task.Task;
import jeff.task.ToDo;

/**
 * Represents a command to edit an existing task in the task list.
 * Depending on the type of task (ToDo, Deadline, Event), the command allows
 * updating the task description, start time, and/or end time.
 */
public class EditCommand extends Command {
    private final int index;
    private final String newDescription;
    private final LocalDateTime newStartTime;
    private final LocalDateTime newEndTime;

    /**
     * Private constructor for creating an EditCommand. This is invoked through static factory methods.
     *
     * @param index The index of the task to be edited in the task list.
     * @param newDescription The new description for the task.
     * @param newStartTime The new start time for an Event task, or null if not applicable.
     * @param newEndTime The new end time for Deadline or Event tasks, or null if not applicable.
     */
    private EditCommand(int index, String newDescription, LocalDateTime newStartTime, LocalDateTime newEndTime) {
        this.index = index;
        this.newDescription = newDescription;
        this.newStartTime = newStartTime;
        this.newEndTime = newEndTime;
    }

    /**
     * Factory method to create an EditCommand for ToDo tasks.
     *
     * @param index The index of the ToDo task to be edited.
     * @param newDescription The new description of the ToDo task.
     * @return A new EditCommand for the ToDo task.
     */
    public static EditCommand forToDoTask(int index, String newDescription) {
        return new EditCommand(index, newDescription, null, null);
    }

    /**
     * Factory method to create an EditCommand for Deadline tasks.
     *
     * @param index The index of the Deadline task to be edited.
     * @param newDescription The new description for the Deadline task.
     * @param newEndTime The new end time for the Deadline task in string format.
     * @return A new EditCommand for the Deadline task.
     * @throws JeffException If the date format is incorrect.
     */
    public static EditCommand forDeadlineTask(int index,
                                              String newDescription,
                                              String newEndTime) throws JeffException {
        return parseTime(index, newDescription, null, newEndTime);
    }

    /**
     * Factory method to create an EditCommand for Event tasks.
     *
     * @param index The index of the Event task to be edited.
     * @param newDescription The new description for the Event task.
     * @param newStartTime The new start time for the Event task in string format.
     * @param newEndTime The new end time for the Event task in string format.
     * @return A new EditCommand for the Event task.
     * @throws JeffException If the date format is incorrect.
     */
    public static EditCommand forEventTask(int index,
                                           String newDescription,
                                           String newStartTime,
                                           String newEndTime) throws JeffException {
        return parseTime(index, newDescription, newStartTime, newEndTime);
    }

    /**
     * Parses date strings into LocalDateTime objects for deadline or event tasks.
     *
     * @param index The index of the task to be edited.
     * @param newDescription The new description for the task.
     * @param newStartTime The new start time (null if not applicable).
     * @param newEndTime The new end time (required for deadlines/events).
     * @return A new EditCommand with parsed times.
     * @throws JeffException If the date format is invalid.
     */
    private static EditCommand parseTime(int index,
                                         String newDescription,
                                         String newStartTime,
                                         String newEndTime) throws JeffException {
        try {
            LocalDateTime startTime = (newStartTime != null)
                    ? LocalDateTime.parse(newStartTime.trim(), Storage.getDateTimeFormatter())
                    : null;
            LocalDateTime endTime = (newEndTime != null)
                    ? LocalDateTime.parse(newEndTime.trim(), Storage.getDateTimeFormatter())
                    : null;

            return new EditCommand(index, newDescription, startTime, endTime);
        } catch (DateTimeParseException e) {
            throw new JeffException("You need to format your dates as follows: " + Storage.getDateFormat());
        }
    }

    /**
     * Executes the EditCommand by updating the relevant task in the task list.
     * The task is replaced with a new version reflecting the provided updates.
     *
     * @param tasks The task list to be updated.
     * @param ui The user interface to display messages.
     * @param storage The storage to update saved tasks.
     * @throws JeffException If the task index is invalid or if the task type is unsupported.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
        if (index < 0 || index >= tasks.size()) {
            throw new JeffException("Invalid task index.");
        }

        Task originalTask = tasks.getTask(index);
        Task updatedTask;

        // Update based on the type of task (Event, Deadline, ToDo)
        if (originalTask instanceof Event event) {
            updatedTask = new Event(
                    newDescription != null ? newDescription : event.getDescription(),
                    newStartTime != null ? newStartTime : event.getFrom(),
                    newEndTime != null ? newEndTime : event.getTo()
            );
        } else if (originalTask instanceof Deadline deadline) {
            updatedTask = new Deadline(
                    newDescription != null ? newDescription : deadline.getDescription(),
                    newEndTime != null ? newEndTime : deadline.getDueDate()
            );
        } else if (originalTask instanceof ToDo todo) {
            updatedTask = new ToDo(
                    newDescription != null ? newDescription : todo.getDescription()
            );
        } else {
            throw new JeffException("Unsupported task type.");
        }

        // Replace the old task with the updated task
        tasks.updateTask(index, updatedTask);
        storage.updateSave(tasks.getTasks(), index);
        ui.showMessage("Task updated: " + updatedTask);
    }
}
