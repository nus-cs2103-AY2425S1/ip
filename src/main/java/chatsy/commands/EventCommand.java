package chatsy.commands;

import chatsy.TaskManager;
import chatsy.exceptions.EmptyDescriptionException;
import chatsy.exceptions.InvalidCommandException;
import chatsy.tasks.EventTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles the "event" command which adds a new event task.
 */
public class EventCommand extends Command {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final String description;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public EventCommand(String arguments) throws InvalidCommandException {
        String[] parts = arguments.split(" /from | /to ");
        if (parts.length < 3 || parts[0].trim().isEmpty()) {
            throw new InvalidCommandException();
        }
        this.description = parts[0].trim();

        try {
            this.startTime = LocalDateTime.parse(parts[1].trim(), DATE_TIME_FORMATTER);
            this.endTime = LocalDateTime.parse(parts[2].trim(), DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException();
        }
    }

    @Override
    public String execute(TaskManager taskManager) throws EmptyDescriptionException {
        taskManager.addTask(new EventTask(description, startTime, endTime));
        return "Got it. I've added this task.\nNow you have " + taskManager.getTasks().size() + " tasks in the list.";
    }
}
