package moody.commands;

import moody.storage.Storage;
import moody.tasks.Deadline;
import moody.tasks.Task;
import moody.tasks.TaskList;
import moody.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a deadline task to the task list.
 * This command takes a description and a deadline date-time, creates a new Deadline task,
 * and then adds it to the task list. The task list is then saved to storage.
 */
public class AddDeadlineCommand extends Command {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected String description;
    protected LocalDateTime deadline;

    /**
     * Creates an AddDeadlineCommand to add a Deadline task with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param deadline The deadline date and time in the format "yyyy-MM-dd HHmm".
     * @throws DateTimeParseException If the deadline string cannot be parsed into a LocalDateTime.
     */
    public AddDeadlineCommand(String description, String deadline) throws DateTimeParseException {
        this.description = description;
        this.deadline = LocalDateTime.parse(deadline, INPUT_FORMATTER);
    }

    public String getDescription() {
        return this.description;
    }

    public String getDateTimeString() {
        return deadline.format(INPUT_FORMATTER);
    }

    /**
     * Executes the command by adding a new Deadline task to the task list,
     * saving the updated task list to storage, and showing the task added message.
     *
     * @param tasks The task list to which the new Deadline task will be added.
     * @param ui The user interface for showing messages.
     * @param storage The storage where the updated task list will be saved.
     * @throws IOException If there is an error while saving the task list to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task newTask = new Deadline(this.description, this.deadline);
        tasks.add(newTask);
        storage.save(tasks.toArrayList());
        ui.showTaskAdded(newTask, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
