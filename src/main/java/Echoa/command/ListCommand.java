package echoa.command;

import echoa.main.Parser;
import echoa.main.Storage;
import echoa.main.TaskList;
import echoa.main.Ui;

/**
 * ListCommand is a class which encapsulates Hi Commands.
 * It extends from Command.
 */
public class ListCommand extends Command {

    public ListCommand(Ui ui, Parser parser, TaskList taskList, Storage storage) {
        super(ui, parser, taskList, storage);
    }

    /**
     * Executes the task by doing nothing.
     *
     * @param line input line given by user.
     */
    public void execute(String line) {
        // do nothing
    }

    /**
     * Returns Echoa's reply to the Find Command, the list of tasks.
     *
     * @return a String message of the list of tasks.
     */
    public String getMessage() {
        return ui.getListOfTasksMessage(taskList);
    }
}
