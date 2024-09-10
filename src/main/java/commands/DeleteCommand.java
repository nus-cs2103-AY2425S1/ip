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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.deleteTask(taskNumber);
            storage.createFile();
            storage.writeFile(tasks.toString());
            ui.say("File saved.");
        } catch (IndexOutOfBoundsException e) {
            ui.say("The task is not in the list.");
        } catch (IOException e) {
            ui.say("File cannot be saved.");
        }
    }
}
