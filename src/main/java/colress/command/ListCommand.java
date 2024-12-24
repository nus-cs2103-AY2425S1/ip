package colress.command;

import static colress.Ui.MESSAGE_LIST_EMPTY;

import colress.UiAdvanced;
import colress.UiBeginner;
import colress.exception.InvalidCommandFormatException;
import colress.parser.Parser;
import colress.tasklist.TaskList;

/**
 * Represents the list command that prints all tasks from the list of tasks.
 */
public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    public ListCommand(String[] arguments) {
        super(arguments);
    }

    @Override
    public String start(UiBeginner ui, TaskList taskList) {
        if (taskList.isEmpty()) {
            return MESSAGE_LIST_EMPTY;
        }
        return execute(ui, taskList);
    }

    /**
     * Facilitates listing all tasks in the provided TaskList object, and using the provided Ui object to print the
     * output for the user.
     */
    @Override
    public String execute(UiBeginner ui, TaskList taskList) {
        return ui.printTasks(taskList);
    }

    @Override
    public String execute(UiAdvanced ui, TaskList taskList) throws InvalidCommandFormatException {
        if (taskList.isEmpty()) {
            return MESSAGE_LIST_EMPTY;
        }
        return ui.printTasks(taskList);
    }

    @Override
    public String toString() {
        return Parser.COMMAND_LIST;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof ListCommand;
    }
}
