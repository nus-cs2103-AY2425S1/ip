package command;

import utility.Storage;
import utility.TaskList;
import utility.Ui;

/**
 * Lists the {@link Task} in the {@link TaskList}.
 */
public class ListCommand extends Command {

    /**
     * No parsing required for this command.
     *
     * @param commandArguments no arguments required for this command.
     * @return same {@link ByeCommand}.
     */
    @Override
    public Command parseArguments(String commandArguments) {
        return this;
    }

    /**
     * Prints to stdout the String representation of the {@link TaskList}.
     *
     * @param taskList of which will be printed.
     * @param storage not used in this command.
     * @return unmodified {@link TaskList}.
     */
    @Override
    public TaskList runCommand(TaskList taskList, Storage storage) {
        Ui.say(taskList.toString());
        return taskList;
    }
}
