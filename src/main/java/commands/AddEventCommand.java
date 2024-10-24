package commands;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import skibidi.Command;
import skibidi.SkibidiException;
import skibidi.Ui;
import storage.Event;
import storage.TaskStorage;

/**
 * Represents a command to add an event task.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String startTime;
    private final String endTime;

    /**
     * Creates a new AddEventCommand.
     *
     * @param input The input string containing the event description, start time and end time.
     * @throws SkibidiException If the input string is in an invalid format.
     */
    public AddEventCommand(String input) throws SkibidiException {
        String[] partsFrom = splitInput(input, "/from ");
        String[] partsTo = splitInput(partsFrom[1], "/to ");

        this.description = extractDescription(partsFrom[0]);
        this.startTime = parseDateTime(partsTo[0].trim());
        this.endTime = parseDateTime(partsTo[1].trim());

        assert description != null && !description.isEmpty() : "Description should not be null or empty";
        assert startTime != null && !startTime.isEmpty() : "Start time should not be null or empty";
        assert endTime != null && !endTime.isEmpty() : "End time should not be null or empty";

    }

    /**
     * Splits the input string into parts based on the delimiter.
     *
     * @param input The input string to be split.
     * @param delimiter The delimiter to split the input string.
     * @return An array containing the split parts.
     * @throws SkibidiException If the input string is in an invalid format.
     */
    private String[] splitInput(String input, String delimiter) throws SkibidiException {
        String[] parts = input.split(delimiter);
        if (parts.length < 2) {
            throw new SkibidiException("Invalid event format. Usage: event [description] " + delimiter + " [time]");
        }
        return parts;
    }

    /**
     * Extracts the description part from the input string.
     *
     * @param part The part of the input string containing the description.
     * @return The description part.
     */
    private String extractDescription(String part) {
        return part.substring(6).trim();
    }

    /**
     * Parses the date and time string into the correct format.
     *
     * @param dateTimeStr The date and time string to be parsed.
     * @return The parsed date and time string.
     * @throws SkibidiException If the date and time string is in an invalid format.
     */
    private String parseDateTime(String dateTimeStr) throws SkibidiException {
        String[] dateTimeParts = dateTimeStr.split(" ", 2);
        String date = dateTimeParts[0];
        String time = dateTimeParts.length > 1 ? dateTimeParts[1] : "00:00"; // Default to "00:00" if time is missing
        return date + "T" + time;
    }

    /**
     * Executes the command to add an event task.
     *
     * @param ui      The user interface to interact with the user.
     * @param storage The task storage to store the task.
     * @return Output message.
     */
    @Override
    public String execute(Ui ui, TaskStorage storage) {
        assert ui != null : "Ui should not be null";
        assert storage != null : "TaskStorage should not be null";
        try {
            Event event = new Event(description, startTime, endTime, false);
            storage.addTask(event);
            return ui.outputMessage("Got it. I've added this task:\n  " + event);
        } catch (SkibidiException | IOException e) {
            return ui.outputMessage(e.getMessage());
        } catch (DateTimeParseException e) {
            return ui.outputMessage("Invalid date format. Please use yyyy-mm-dd hh:mm.");
        }
    }
}
