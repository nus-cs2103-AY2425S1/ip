package chatsy.commands;

import chatsy.TaskManager;
import chatsy.exceptions.EmptyDescriptionException;
import chatsy.exceptions.InvalidCommandException;
import chatsy.tasks.DeadlineTask;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles the "deadline" command which adds a new deadline task.
 */
public class DeadlineCommand extends Command {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final String description;
    private final LocalDate dueDate;

    public DeadlineCommand(String arguments) throws InvalidCommandException {
        String[] parts = arguments.split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            throw new InvalidCommandException();
        }
        this.description = parts[0].trim();

        try {
            this.dueDate = LocalDate.parse(parts[1].trim(), DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException();
        }
    }

    @Override
    public String execute(TaskManager taskManager) throws EmptyDescriptionException {
        taskManager.addTask(new DeadlineTask(description, dueDate));
        return "Got it. I've added this task.\nNow you have " + taskManager.getTasks().size() + " tasks in the list.";
    }
}
