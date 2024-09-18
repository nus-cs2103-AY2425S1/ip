package commands;

import java.io.IOException;

import cook.Storage;
import cook.TaskList;
import exceptions.InvalidInputException;

/**
 * DeleteCommand class to process delete commands.
 */
public class DeleteCommand extends Command {
    protected int taskNumber;

    /**
     * Constructs DeleteCommand object using int.
     *
     * @param taskNumber Number of the task to delete.
     */
    public DeleteCommand(int taskNumber) {
        super("delete");
        this.taskNumber = taskNumber;
    }

    /**
     * Constructs DeleteCommand object using String.
     *
     * @param taskNumberString Number of the task to delete in String.
     * @throws InvalidInputException If input is not understandable.
     */
    public DeleteCommand(String taskNumberString) throws InvalidInputException {
        super("delete");
        try {
            this.taskNumber = Integer.parseInt(taskNumberString);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("A task must be selected.");
        }
    }

    /**
     * Deletes task and saves tasks to file.
     *
     * @param tasks List of Task objects.
     * @param storage Class to save and load tasks on the local drive.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder content = new StringBuilder();
        try {
            tasks.deleteTask(taskNumber);
            content.append("Task deleted.\n");
        } catch (IndexOutOfBoundsException e) {
            return "The task is not in the list.\n";
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
