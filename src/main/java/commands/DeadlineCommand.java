package commands;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import cook.Storage;
import cook.TaskList;
import exceptions.InvalidInputException;
import tasks.Deadline;

/**
 * DeadlineCommand class to process Deadline commands.
 */
public class DeadlineCommand extends Command {
    protected String description;
    protected LocalDateTime by;

    /**
     * Constructs DeadlineCommand object.
     *
     * @param description Description of the Deadline Task.
     * @param by When to finish the task by.
     * @throws InvalidInputException If input is not understandable.
     */
    public DeadlineCommand(String description, String by) throws InvalidInputException {
        super("deadline");
        this.description = description;
        try {
            by = by.strip().replace(" ", "T");
            this.by = LocalDateTime.parse(by);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Date & time must be in a valid format, e.g. YYYY-MM-DD HH:mm.");
        } catch (NullPointerException e) {
            throw new InvalidInputException("Deadline command format: deadline <description> /by <YYYY-MM-DD HH:mm>.");
        }
    }

    /**
     * Adds Deadline task and saves tasks to file.
     *
     * @param tasks List of Task objects.
     * @param storage Class to save and load tasks on the local drive.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Deadline deadline = new Deadline(this.description, this.by);
        if (tasks.detectDuplicate(deadline)) {
            return "There is already another task with the same description.";
        }
        StringBuilder content = new StringBuilder();
        tasks.addTask(deadline);
        content.append("Deadline task has been added.\n");
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
