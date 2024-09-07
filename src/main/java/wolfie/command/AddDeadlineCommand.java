package wolfie.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import wolfie.exception.WolfieException;
import wolfie.task.Deadline;
import wolfie.task.Task;
import wolfie.task.TaskList;
import wolfie.util.Storage;
import wolfie.util.Ui;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class AddDeadlineCommand extends Command {
    private final String arguments;

    /**
     * Constructs an AddDeadlineCommand object to add a deadline task to the task list.
     *
     * @param arguments The arguments passed to the command.
     */
    public AddDeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the add deadline command to add a deadline task to the task list.
     *
     * @param tasks The task list to add the task to.
     * @param ui The user interface to display messages.
     * @param storage The storage to save the task list to.
     * @return The message to show the user.
     * @throws IOException If there is an error saving the task list.
     * @throws WolfieException If the description or deadline is empty.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, WolfieException {
        String[] parts = arguments.split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new WolfieException("The description and deadline of a deadline task cannot be empty.");
        }
        String description = parts[0].trim();
        LocalDateTime by = LocalDateTime.parse(parts[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        Task task = new Deadline(description, by, false);
        boolean isAdded = tasks.add(task);
        if (isAdded) {
            storage.save(tasks);
            return ui.showTaskAdded(task, tasks.size());
        } else {
            return "Task already exists in the list. " + task.getDescription();
        }
    }
}
