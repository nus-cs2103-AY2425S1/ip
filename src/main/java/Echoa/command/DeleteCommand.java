package echoa.command;

import echoa.exception.InvalidIndexInputException;
import echoa.exception.ListOutOfBoundsException;
import echoa.main.Parser;
import echoa.main.Storage;
import echoa.main.TaskList;
import echoa.main.Ui;
import echoa.task.Task;

import java.io.IOException;

/**
 * DeleteCommand is a class which encapsulates Delete Commands.
 * It extends from Command.
 */
public class DeleteCommand extends Command {

    private int index;
    private Task task;

    public DeleteCommand(Ui ui, Parser parser, TaskList taskList, Storage storage) {
        super(ui, parser, taskList, storage);
    }

    /**
     * Deletes the task at the label given.
     *
     * @param line input line given by user.
     */
    @Override
    public void execute(String line) throws InvalidIndexInputException, ListOutOfBoundsException, IOException {
        index = parser.parseIndex(line);
        task = taskList.getSpecificTask(index);
        taskList.deleteTask(index);
        storage.handleChange(taskList);
    }

    /**
     * Returns Echoa's reply to the Delete Command, the deleted task.
     *
     * @return a String message of the deleted task.
     */
    public String getMessage() {
        return ui.getDeleteTaskMessage(taskList, task);
    }
}
