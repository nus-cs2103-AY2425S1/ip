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

    public AddDeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, WolfieException {
        String[] parts = arguments.split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new WolfieException("The description and deadline of a deadline task cannot be empty.");
        }
        String description = parts[0].trim();
        LocalDateTime by = LocalDateTime.parse(parts[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        Task task = new Deadline(description, by, false);
        tasks.add(task);
        storage.save(tasks);
        return ui.showTaskAdded(task, tasks.size()); // Show the task added message
    }
}
