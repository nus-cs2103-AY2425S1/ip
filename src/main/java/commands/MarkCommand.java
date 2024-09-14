package commands;

import java.io.IOException;

import cook.Storage;
import cook.TaskList;
import exceptions.InvalidInputException;

/**
 * MarkCommand class to process mark commands.
 */
public class MarkCommand extends Command {
    protected int taskNumber;

    /**
     * Constructs MarkCommand object using int.
     *
     * @param taskNumber Number of the task to mark.
     */
    public MarkCommand(int taskNumber) {
        super("mark");
        this.taskNumber = taskNumber;
    }

    /**
     * Constructs MarkCommand object using String.
     *
     * @param taskNumberString Number of the task to mark in String.
     * @throws InvalidInputException If input is not understandable.
     */
    public MarkCommand(String taskNumberString) throws InvalidInputException {
        super("mark");
        try {
            this.taskNumber = Integer.parseInt(taskNumberString);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("A task must be selected.");
        }
    }

    /**
     * Marks task and saves tasks to file.
     *
     * @param tasks List of Task objects.
     * @param storage Class to save and load tasks on the local drive.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder content = new StringBuilder();
        try {
            tasks.markTask(this.taskNumber);
            content.append("Task marked.\n");
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
