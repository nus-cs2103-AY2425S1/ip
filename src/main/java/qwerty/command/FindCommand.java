package qwerty.command;

import java.util.HashMap;

import qwerty.QwertyException;
import qwerty.Storage;
import qwerty.TaskList;
import qwerty.ui.Ui;

/**
 * This class encapsulates the 'find' command.
 */
public class FindCommand extends Command {

    public FindCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    /**
     * Searches for and prints the tasks in the task list that contain a given substring.
     *
     * @param tasks The task list.
     * @param ui The Ui object.
     * @param storage The storage object.
     * @throws QwertyException If no substring is given.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws QwertyException {
        String substring = getArgs().get("main");
        if (substring == null) {
            throw new QwertyException("You need to provide a string to search for.");
        }
        String taskString = tasks.findAndListTasks(substring);
        ui.showQwertyMessage("\nHere are the matching tasks in your list:"
                + taskString);
    }
}
