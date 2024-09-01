package barney.action.commands;

import java.util.HashMap;

import barney.data.TaskList;
import barney.data.exception.InvalidArgumentException;
import barney.ui.Ui;

/**
 * Represents the command to find tasks in the TaskList. Extends the
 * {@link Command} class.
 */
public class FindCommand extends Command {

    /**
     * Constructs a new FindCommand object.
     *
     * @param argumentMap A HashMap containing the arguments for the command.
     */
    public FindCommand(HashMap<String, String> argumentMap) {
        super("find", argumentMap);
    }

    /**
     * Executes the FindCommand, which finds tasks in the TaskList that match the
     * keyword.
     *
     * @param tasks The TaskList to search for matching tasks.
     * @param ui    The Ui object used to print messages.
     * @return true if the command is executed successfully, false otherwise.
     * @throws InvalidArgumentException if the command arguments are invalid.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags();

        String keyword = getParameter("keyword");

        TaskList matchingTasks = tasks.find(keyword);
        ui.printMatchingTasks(matchingTasks);

        return true;
    }
}
