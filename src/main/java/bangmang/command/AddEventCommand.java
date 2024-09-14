package LittleMissHelpful.Command;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import LittleMissHelpful.Storage.Storage;
import LittleMissHelpful.Tasks.TaskList;
import LittleMissHelpful.Ui.Ui;
import LittleMissHelpful.Exception.InvalidCommandException;
import LittleMissHelpful.Tasks.Event;

public class AddEventCommand extends Command {
    private final String description;
    private final String fromString;
    private final String toString;

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
            throw new InvalidCommandException("Invalid date format. Please use 'yyyy-MM-ddTHH:mm' or 'yyyy-MM-dd'.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private LocalDateTime parseDateTime(String dateTimeString) throws DateTimeParseException {
        /**
         * Parses string into LocalDateTime
         */

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