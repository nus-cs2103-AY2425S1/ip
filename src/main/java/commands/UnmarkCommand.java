package commands;

import java.io.IOException;

import cook.Storage;
import cook.TaskList;
import exceptions.InvalidInputException;

/**
 * UnmarkCommand class to process unmark commands.
 */
public class UnmarkCommand extends Command {
    protected int taskNumber;

    /**
     * Constructs UnmarkCommand object using int.
     *
     * @param taskNumber Number of the task to mark.
     */
    public UnmarkCommand(int taskNumber) {
        super("unmark");
        this.taskNumber = taskNumber;
    }

    /**
     * Constructs UnmarkCommand object using String.
     *
     * @param taskNumberString Number of the task to mark in String.
     * @throws InvalidInputException If input is not understandable.
     */
    public UnmarkCommand(String taskNumberString) throws InvalidInputException {
        super("unmark");
        try {
            this.taskNumber = Integer.parseInt(taskNumberString);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("A task must be selected.");
        }
    }

    /**
     * Unmarks task and saves file.
     *
     * @param tasks List of Task objects.
     * @param storage Class to save and load tasks on the local drive.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder content = new StringBuilder();
        try {
            tasks.unmarkTask(this.taskNumber);
            content.append("Task unmarked.\n");
        } catch (IndexOutOfBoundsException e) {
            return "The task is not in the list.";
        }
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
