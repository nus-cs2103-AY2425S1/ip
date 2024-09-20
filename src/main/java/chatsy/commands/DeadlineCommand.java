package chatsy.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chatsy.TaskManager;
import chatsy.exceptions.EmptyDescriptionException;
import chatsy.exceptions.InvalidCommandException;
import chatsy.tasks.DeadlineTask;

/**
 * Handles the "deadline" command which adds a new deadline task to the task manager.
 * The deadline is specified in the form of a description followed by a due date.
 */
public class DeadlineCommand extends Command {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final String description;
    private final LocalDate dueDate;

    /**
     * Creates a new DeadlineCommand with the given arguments.
     * The arguments must contain a task description and a due date, separated by "/by".
     *
     * @param arguments The input string containing the description and due date.
     * @throws InvalidCommandException If the description is empty or the date is invalid.
     */
    public DeadlineCommand(String arguments) throws InvalidCommandException {
        String[] parts = arguments.split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            throw new InvalidCommandException("Invalid command: Description or date missing.");
        }
        this.description = parts[0].trim();

        try {
            this.dueDate = LocalDate.parse(parts[1].trim(), DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    /**
     * Executes the deadline command by adding a new {@link DeadlineTask} to the task manager.
     *
     * @param taskManager The task manager where the new deadline task is added.
     * @return A string response confirming the task has been added.
     * @throws EmptyDescriptionException If the task description is empty.
     */
    @Override
    public String execute(TaskManager taskManager) throws EmptyDescriptionException {
        taskManager.addTask(new DeadlineTask(description, dueDate));
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                description, taskManager.getTasks().size());
    }
}
