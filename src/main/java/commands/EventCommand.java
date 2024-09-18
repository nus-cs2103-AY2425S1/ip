package commands;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import cook.Storage;
import cook.TaskList;
import exceptions.InvalidInputException;
import tasks.Event;

/**
 * EventCommand class to process Event commands.
 */
public class EventCommand extends Command {
    protected String description;
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs EventCommand object.
     *
     * @param description Description of the Event Task.
     * @param from When the event starts.
     * @param to When the event ends.
     * @throws InvalidInputException If input is not understandable.
     */
    public EventCommand(String description, String from, String to) throws InvalidInputException {
        super("event");
        this.description = description;

        try {
            from = from.strip().replace(" ", "T");
            to = to.strip().replace(" ", "T");
            this.from = LocalDateTime.parse(from);
            this.to = LocalDateTime.parse(to);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Date & time must be in a valid format, e.g. YYYY-MM-DD HH:mm.");
        } catch (NullPointerException e) {
            throw new InvalidInputException("Event command format: event <description> "
                    + "/from <YYYY-MM-DD HH:mm> /to <YYYY-MM-DD HH:mm>.");
        }

        if (this.from.isAfter(this.to)) {
            throw new InvalidInputException("The starting date & time cannot be "
                    + "after the ending date & time.");
        }
    }

    /**
     * Adds Event task and saves tasks to file.
     *
     * @param tasks List of Task objects.
     * @param storage Class to save and load tasks on the local drive.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Event event = new Event(this.description, this.from, this.to);
        if (tasks.detectDuplicate(event)) {
            return "There is already another task with the same description.";
        }
        StringBuilder content = new StringBuilder();
        tasks.addTask(event);
        content.append("Event task has been added.\n");
        try {
            storage.writeFile(tasks);
            content.append("File saved.");
            return content.toString();
        } catch (IOException e) {
            content.append("File cannot be saved.");
            return content.toString();
        }
    }
}
