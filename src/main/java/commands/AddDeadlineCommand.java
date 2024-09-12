package commands;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import skibidi.Command;
import skibidi.SkibidiException;
import skibidi.Ui;
import storage.Deadline;
import storage.TaskStorage;

/**
 * Represents a command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final String deadline;

    /**
     * Creates a new AddDeadlineCommand.
     *
     * @param input The input string containing the deadline description and deadline date.
     * @throws SkibidiException If the input string is in an invalid format.
     */
    public AddDeadlineCommand(String input) throws SkibidiException {
        String[] parts = splitInput(input);
        this.description = extractDescription(parts[0]);
        this.deadline = parseDeadline(parts[1]);
    }

    /**
     * Splits the input string into description and deadline parts.
     *
     * @param input The input string containing the deadline description and deadline date.
     * @return An array containing the description and deadline parts.
     * @throws SkibidiException If the input string is in an invalid format.
     */
    private String[] splitInput(String input) throws SkibidiException {
        String[] parts = input.split("/by ");
        if (parts.length < 2) {
            throw new SkibidiException("Invalid deadline format. Usage: deadline [description] /by [date]");
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
        return part.substring(9).trim();
    }

    /**
     * Parses the deadline string into the correct format.
     *
     * @param part The part of the input string containing the deadline date.
     * @return The parsed deadline string.
     */
    private String parseDeadline(String part) {
        String[] dateTimeParts = part.trim().split(" ", 2);
        String date = dateTimeParts[0];
        String time = dateTimeParts.length > 1 ? dateTimeParts[1] : "00:00"; // Default to "00:00" if time missing
        return date + "T" + time;
    }

    /**
     * Executes the command to add a deadline task.
     *
     * @param ui      The user interface to interact with the user.
     * @param storage The task storage to store the task.
     * @return Output message.
     */
    @Override
    public String execute(Ui ui, TaskStorage storage) {
        try {
            Deadline deadline = new Deadline(description, this.deadline, false);
            storage.addTask(deadline);
            return ui.outputMessage("Got it. I've added this task:\n  " + deadline);
        } catch (DateTimeParseException e) {
            return ui.outputMessage("Invalid date format. Please use yyyy-mm-dd hh:mm.");
        } catch (SkibidiException | IOException e) {
            return ui.outputMessage(e.getMessage());
        }
    }
}
