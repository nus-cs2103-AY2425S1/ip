package colress.command;

import colress.ColressUiAdvanced;
import colress.ColressUiBeginner;
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
    public String start(ColressUiBeginner ui, TaskList taskList) {
        return execute(ui, taskList);
    }

    /**
     * Facilitates listing all tasks in the provided TaskList object, and using the provided Ui object to print the
     * output for the user.
     */
    @Override
    public String execute(ColressUiBeginner ui, TaskList taskList) {
        return ui.printTasks(taskList);
    }

    @Override
    public String execute(ColressUiAdvanced ui, TaskList taskList) throws InvalidCommandFormatException {
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
