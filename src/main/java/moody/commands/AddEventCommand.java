package moody.commands;

import moody.storage.Storage;
import moody.tasks.Event;
import moody.tasks.Task;
import moody.tasks.TaskList;
import moody.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add an event task to the task list.
 * This command takes a description, start time, and end time, creates a new Event task,
 * and then adds it to the task list. The task list is then saved to storage.
 */
public class AddEventCommand extends Command {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected String description;
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    /**
     * Creates an AddEventCommand to add an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param startTime The start date and time of the event in the format "yyyy-MM-dd HHmm".
     * @param endTime The end date and time of the event in the format "yyyy-MM-dd HHmm".
     * @throws DateTimeParseException If the startTime or endTime strings cannot be parsed into LocalDateTime.
     */
    public AddEventCommand(String description, String startTime, String endTime) throws DateTimeParseException {
        this.description = description;
        this.startTime = LocalDateTime.parse(startTime, INPUT_FORMATTER);
        this.endTime = LocalDateTime.parse(endTime, INPUT_FORMATTER);
    }

    /**
     * Executes the command by adding a new Event task to the task list,
     * saving the updated task list to storage, and showing the task added message.
     *
     * @param tasks The task list to which the new Event task will be added.
     * @param ui The user interface for showing messages.
     * @param storage The storage where the updated task list will be saved.
     * @throws IOException If there is an error while saving the task list to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task newTask = new Event(description, startTime, endTime);
        tasks.add(newTask);
        storage.save(tasks.toArrayList());
        ui.showTaskAdded(newTask, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
