package bangmang.command;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import bangmang.storage.Storage;
import bangmang.tasks.TaskList;
import bangmang.ui.Ui;
import bangmang.exception.InvalidCommandException;
import bangmang.tasks.Event;

/**
 * Represents a command to add an event task.
 */

public class AddEventCommand extends Command {
    private final String description;
    private final String fromString;
    private final String toString;

    /**
     * Constructs an AddEventCommand with the specified description, start time, and end time.
     *
     * @param description A description of the event task.
     * @param fromString The start time of the event as a String, which may be in the format 'yyyy-MM-ddTHH:mm' or 'yyyy-MM-dd'.
     * @param toString The end time of the event as a String, which may be in the format 'yyyy-MM-ddTHH:mm' or 'yyyy-MM-dd'.
     */
    public AddEventCommand(String description, String fromString, String toString) {
        this.description = description;
        this.fromString = fromString;
        this.toString = toString;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        LocalDateTime from;
        LocalDateTime to;

        try {
            from = parseDateTime(fromString);
            to = parseDateTime(toString);

            Event event = new Event(description, from, to);
            tasks.add(event);
            storage.save(tasks.getTasks());
            return ui.showAddedNewTask(event, tasks);

        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Alamak, invalid date format. Please use 'yyyy-MM-ddTHH:mm' or 'yyyy-MM-dd'.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Parses a string into a LocalDateTime object.
     *
     * @param dateTimeString The string representing the date and time.
     * @return A LocalDateTime object.
     * @throws DateTimeParseException if the string cannot be parsed into a LocalDateTime.
     */
    private LocalDateTime parseDateTime(String dateTimeString) throws DateTimeParseException {
        if (dateTimeString.equalsIgnoreCase("today")) {
            // Use current date and set time to 00:00 (start of the day)
            return LocalDateTime.now().withHour(00).withMinute(00);
        }
        try {
            // Try parsing as LocalDateTime
            return LocalDateTime.parse(dateTimeString);
        } catch (DateTimeParseException e) {
            try {
                // If LocalDateTime parsing fails, try parsing as LocalDate
                LocalDate date = LocalDate.parse(dateTimeString);
                return date.atTime(00, 00);
            } catch (DateTimeParseException ex) {
                throw ex;
            }
        }
    }
}
