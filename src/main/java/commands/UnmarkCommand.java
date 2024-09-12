package commands;

import java.io.IOException;

import cook.Storage;
import cook.TaskList;
import cook.Ui;
import exceptions.InvalidInputException;

/**
 * UnmarkCommand class to process unmark commands.
 */
public class UnmarkCommand extends Command {
    protected int taskNumber;

    /**
     * Constructor for UnmarkCommand class.
     */
    public UnmarkCommand(String taskNumber) throws InvalidInputException {
        super("unmark");
        try {
            this.taskNumber = Integer.parseInt(taskNumber);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("A task must be selected.");
        }
    }

    /**
     * Unmarks task and saves file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder content = new StringBuilder();
        try {
            tasks.unmarkTask(this.taskNumber);
            content.append("Task marked.");
        } catch (IndexOutOfBoundsException e) {
            return "The task is not in the list.";
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
