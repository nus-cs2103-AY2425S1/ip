package commands;

import java.io.IOException;

import cook.Storage;
import cook.TaskList;
import cook.Ui;
import exceptions.InvalidInputException;

/**
 * MarkCommand class to process mark commands.
 */
public class MarkCommand extends Command {
    protected int taskNumber;

    /**
     * Constructor for MarkCommand class.
     */
    public MarkCommand(String taskNumber) throws InvalidInputException {
        super("mark");
        try {
            this.taskNumber = Integer.parseInt(taskNumber);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("A task must be selected.");
        }
    }

    /**
     * Marks task and saves file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder content = new StringBuilder();
        try {
            tasks.markTask(this.taskNumber);
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
