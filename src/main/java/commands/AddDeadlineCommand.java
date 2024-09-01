package commands;

import skibidi.Command;
import skibidi.SkibidiException;
import skibidi.Ui;
import storage.TaskStorage;
import storage.Deadline;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private String deadline;

    /**
     * Creates a new AddDeadlineCommand.
     *
     * @param input The input string containing the deadline description and deadline date.
     * @throws SkibidiException If the input string is in an invalid format.
     */
    public AddDeadlineCommand(String input) throws SkibidiException{
        String[] parts = input.split("/by ");
        if (parts.length < 2) {
            throw new SkibidiException("Invalid deadline format. Usage: deadline [description] /by [date]");
        } else {
            this.description = parts[0].substring(9).trim();
            String[] dateTimeParts = parts[1].trim().split(" ", 2);
            String date = dateTimeParts[0];
            String time = dateTimeParts.length > 1 ? dateTimeParts[1] : "00:00";  // Default to "00:00" if time is missing

            this.deadline = date + "T" + time;
        }
    }

    /**
     * Executes the command to add a deadline task.
     *
     * @param ui The user interface to interact with the user.
     * @param storage The task storage to store the task.
     * @return True to continue running the program.
     */
    @Override
    public boolean execute(Ui ui, TaskStorage storage) {
        try {
            Deadline deadline = new Deadline(description, this.deadline, false);
            storage.addTask(deadline);
            ui.printMessage("Got it. I've added this task:\n  " + deadline);
        } catch (DateTimeParseException e) {
            ui.printMessage("Invalid date format. Please use yyyy-mm-dd hh:mm.");
        } catch (SkibidiException | IOException e) {
            ui.printMessage(e.getMessage());
        }
        return true;
    }
}
