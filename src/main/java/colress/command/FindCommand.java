package colress.command;

import colress.Parser;
import colress.TaskList;
import colress.Ui;

/**
 * Represents the find command which prints a list of tasks that contain a specified keyword
 */
public final class FindCommand extends ListCommand {

    private String keyword;
    public FindCommand() {
        super();
    }

    @Override
    public String start(Ui ui, TaskList taskList) {
        return ui.promptKeyword(taskList);
    }

    public void initialise(String input) {
        this.keyword = input;
    }

    /**
     * Facilitates listing all tasks in the provided TaskList object that contain a specified keyword,
     * using the provided Ui object to receive date input from the user and to print the output for the user.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        return ui.printTasks(taskList, keyword);
    }

    @Override
    public String toString() {
        return Parser.COMMAND_FIND;
    }
}
