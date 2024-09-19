package echoa.command;

import echoa.exception.InvalidIndexInputException;
import echoa.exception.ListOutOfBoundsException;
import echoa.main.Parser;
import echoa.main.Storage;
import echoa.main.TaskList;
import echoa.main.Ui;

import java.io.IOException;

/**
 * MarkCommand is a class which encapsulates Mark Commands.
 * It extends from Command.
 */
public class MarkCommand extends Command {

    private int index;

    public MarkCommand(Ui ui, Parser parser, TaskList taskList, Storage storage) {
        super(ui, parser, taskList, storage);
    }

    /**
     * Marks the task at the label given.
     *
     * @param line input line given by user.
     */
    @Override
    public void execute(String line) throws InvalidIndexInputException, ListOutOfBoundsException, IOException {
        index = parser.parseIndex(line);
        taskList.markTaskAsDone(index);
        storage.handleChange(taskList);
    }

    /**
     * Returns Echoa's reply to the Mark Command, the marked task.
     *
     * @return a String message of the marked task.
     */
    @Override
    public String getMessage() {
        return ui.getMarkTaskMessage(taskList, index);
    }
}
