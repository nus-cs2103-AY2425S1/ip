package echoa.command;

import echoa.exception.InvalidIndexInputException;
import echoa.exception.ListOutOfBoundsException;
import echoa.main.Parser;
import echoa.main.Storage;
import echoa.main.TaskList;
import echoa.main.Ui;

import java.io.IOException;

/**
 * UnmarkCommand is a class which encapsulates Unmark Commands.
 * It extends from Command.
 */
public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(Ui ui, Parser parser, TaskList taskList, Storage storage) {
        super(ui, parser, taskList, storage);
    }

    /**
     * Unmarks the task at the label given.
     *
     * @param line input line given by user.
     */
    @Override
    public void execute(String line) throws InvalidIndexInputException, ListOutOfBoundsException, IOException {
        index = parser.parseIndex(line);
        taskList.markTaskAsUndone(index);
        storage.handleChange(taskList);
    }

    /**
     * Returns Echoa's reply to the Unmark Command, the unmarked task.
     *
     * @return a String message of the unmarked task.
     */
    @Override
    public String getMessage() {
        return ui.getUnmarkTaskMessage(taskList, index);
    }
}
