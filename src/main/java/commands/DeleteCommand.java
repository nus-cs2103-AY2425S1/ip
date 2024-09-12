package commands;

import java.io.IOException;

import cook.Storage;
import cook.TaskList;
import cook.Ui;
import exceptions.InvalidInputException;

/**
 * DeleteCommand class to process delete commands.
 */
public class DeleteCommand extends Command {
    protected int taskNumber;

    /**
     * Constructor for DeleteCommand class.
     */
    public DeleteCommand(String taskNumber) throws InvalidInputException {
        super("delete");
        try {
            this.taskNumber = Integer.parseInt(taskNumber);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("A task must be selected.");
        }
    }

    /**
     * Deletes task and saves file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder content = new StringBuilder();
        try {
            tasks.deleteTask(taskNumber);
            content.append("Task deleted.\n");
        } catch (IndexOutOfBoundsException e) {
            return "The task is not in the list.\n";
        }
        try {
            storage.createFile();
            storage.writeFile(tasks.toString());
            content.append("File saved.");
            return content.toString();
        } catch (IOException e) {
            content.append("File cannot be saved.");
            return content.toString();
        }
    }
}
