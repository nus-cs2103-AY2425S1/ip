package vinegar.command;

import vinegar.storage.Storage;
import vinegar.ui.Ui;
import vinegar.VinegarException;
import vinegar.Validator;
import vinegar.task.TaskList;
import vinegar.task.Task;
import vinegar.task.Deadline;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Command for adding a new Deadline task.
 * <p>
 * The AddDeadlineCommand allows the user to add a new deadline task to the task list. It checks
 * for valid input, parses the date and time, creates the task, and saves it in the storage.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructs an AddDeadlineCommand with the specified input parts.
     *
     * @param inputParts The command input, where inputParts[1] contains the task description and deadline.
     * @throws VinegarException If the input is invalid or the deadline format is incorrect.
     */
    public AddDeadlineCommand(String[] inputParts) throws VinegarException {
        Validator.validateParts(inputParts, 2, "Please specify the description and deadline.");
        String[] deadlineParts = inputParts[1].split(" /by ");

        // Validate if split operation resulted in two parts
        if (deadlineParts.length < 2) {
            throw new VinegarException("Please specify the deadline using /by in the format yyyy-MM-dd HH:mm.");
        }

        this.description = deadlineParts[0];
        try {
            // Attempt to parse the date/time here
            LocalDateTime.parse(deadlineParts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            this.by = deadlineParts[1];
        } catch (DateTimeParseException e) {
            throw new VinegarException("Invalid date format. Please use yyyy-MM-dd HH:mm format.");
        }
    }

    /**
     * Executes the command to add a deadline task to the task list.
     *
     * @param tasks   The task list to modify.
     * @param ui      The UI for displaying messages to the user.
     * @param storage The storage for saving the task.
     * @throws VinegarException If an error occurs during execution.
     * @throws IOException      If an error occurs while saving the task to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws VinegarException, IOException {
        Task deadlineTask = new Deadline(description, by);
        tasks.addTask(deadlineTask);
        storage.save(tasks.getTasks());
        return ui.printTaskAdded(deadlineTask, tasks.size());
    }
}
