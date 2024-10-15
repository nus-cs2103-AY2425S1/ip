package king.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.task.Event;
import king.ui.Ui;

/**
 * Represents a command to create an event and add it to the task list.
 */
public class EventCommand extends Command {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private String input;

    /**
     * Creates an EventCommand with the specified input.
     *
     * @param input The input string containing the event details.
     */
    public EventCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the event command by creating an event with the specified details,
     * adding it to the task list, saving the updated task list to storage, and
     * displaying the updated task list.
     *
     * @param tasks The task list to which the event will be added.
     * @param ui The user interface to display messages and the updated task list.
     * @param storage The storage to save the updated task list.
     * @throws KingException If the input format is incorrect or the date format is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KingException {
        if (input == null || input.isBlank()) {
            throw new KingException("Input cannot be null or empty.");
        }

        String[] parts = input.split(" /from | /to ");
        if (parts.length != 3) {
            throw new KingException("Invalid event format. Please use the format: description /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm.");
        }

        String description = parts[0].trim();
        String fromDate = parts[1].trim();
        String toDate = parts[2].trim();

        try {
            LocalDateTime parsedFromDate = LocalDateTime.parse(fromDate, DATE_FORMATTER);
            LocalDateTime parsedToDate = LocalDateTime.parse(toDate, DATE_FORMATTER);

            if (parsedFromDate.isAfter(parsedToDate)) {
                throw new KingException("Start time must be before end time.");
            }

            Event event = new Event(description, parsedFromDate, parsedToDate);
            tasks.add(event);
            storage.save(tasks.getTaskList());
            return ui.showTaskAdded(event, tasks.size());
        } catch (DateTimeParseException e) {
            throw new KingException("Invalid date format. Please use yyyy-MM-dd HHmm.");
        }
    }

    /**
     * Indicates whether this command signals the application to exit.
     *
     * @return {@code false} as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
