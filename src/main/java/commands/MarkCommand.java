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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markTask(this.taskNumber);
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
