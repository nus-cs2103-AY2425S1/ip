package chatsy.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chatsy.TaskManager;
import chatsy.exceptions.EmptyDescriptionException;
import chatsy.exceptions.InvalidCommandException;
import chatsy.tasks.EventTask;

/**
 * Handles the "event" command which adds a new event task.
 * The command requires a description, start time, and end time in the format yyyy-MM-dd HH:mm.
 */
public class EventCommand extends Command {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final String description;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Constructs an EventCommand object.
     *
     * @param arguments The arguments for the event in the format: {@code description /from start time /to end time}
     * @throws InvalidCommandException if the command format is invalid or if the date is incorrectly formatted
     */
    public EventCommand(String arguments) throws InvalidCommandException {
        String[] parts = arguments.split(" /from | /to ");
        if (parts.length < 3 || parts[0].trim().isEmpty()) {
            throw new InvalidCommandException("Invalid command format. "
                    + "Please use: event <description> /from <start time> /to <end time>");
        }
        this.description = parts[0].trim();

        try {
            this.startTime = LocalDateTime.parse(parts[1].trim(), DATE_TIME_FORMATTER);
            this.endTime = LocalDateTime.parse(parts[2].trim(), DATE_TIME_FORMATTER);

            // Validation: Check that the start time is before the end time
            if (startTime.isAfter(endTime) || startTime.isEqual(endTime)) {
                throw new InvalidCommandException("Start time must be before end time.");
            }
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Invalid date format. Please use: yyyy-MM-dd HH:mm");
        }
    }

    /**
     * Executes the command by adding a new event task to the TaskManager.
     *
     * @param taskManager The task manager to which the task will be added
     * @return A confirmation message of the task being added
     * @throws EmptyDescriptionException if the task description is empty
     */
    @Override
    public String execute(TaskManager taskManager) throws EmptyDescriptionException {
        taskManager.addTask(new EventTask(description, startTime, endTime));
        return "Got it. I've added this task.\nNow you have " + taskManager.getTasks().size() + " tasks in the list.";
    }
}
