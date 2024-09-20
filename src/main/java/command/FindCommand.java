package command;

import blitz.Storage;
import blitz.TaskList;
import blitz.Ui;
import exception.BlitzEmptyTaskListException;
import exception.BlitzException;

/**
 * Represents a "find" command in the Blitz application.
 */
public class FindCommand extends Command {
    private String parameter;

    /**
     * Constructs a new CommandFind object with specified command String and a parameter String.
     *
     * @param command Command String to be associated with this Command object.
     * @param parameter String containing the parameter for this command.
     */
    public FindCommand(String command, String parameter) {
        super(command);
        this.parameter = parameter;
    }

    /**
     * Executes the command.
     *
     * @param list TaskList to find the tasks containing the parameter.
     * @param ui Ui to print the required text.
     * @param storage Storage to be used if required.
     * @return Execution result of the command as String.
     * @throws BlitzException If TaskList is empty or no matching item found.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws BlitzException {
        if (list.isEmpty()) {
            throw new BlitzEmptyTaskListException();
        }

        if (parameter.equals("::")) {
            throw new BlitzEmptyTaskListException();
        }

        TaskList matchedTasks = list.getMatchedTasks(parameter);

        if (matchedTasks.isEmpty()) {
            throw new BlitzEmptyTaskListException();
        }

        return ui.getStringForTasksMatched(matchedTasks);
    }
}
