package echoa.command;

import echoa.main.Parser;
import echoa.main.Storage;
import echoa.main.TaskList;
import echoa.main.Ui;

/**
 * FindCommand is a class which encapsulates Hi Commands.
 * It extends from Command.
 */
public class FindCommand extends Command {

    private TaskList newTaskList;

    public FindCommand(Ui ui, Parser parser, TaskList taskList, Storage storage) {
        super(ui, parser, taskList, storage);
    }

    /**
     * Finds tasks containing the keyword in the line.
     *
     * @param line input line given by user.
     */
    public void execute(String line) {
        newTaskList = parser.parseFindTask(taskList, line);
    }

    /**
     * Returns Echoa's reply to the Find Command, the list of tasks containing the keyword.
     *
     * @return a String message of a list of tasks containing the keyword.
     */
    public String getMessage() {
        return ui.getListOfTasksMessage(newTaskList);
    }
}
