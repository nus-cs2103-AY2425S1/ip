package command;

import utility.Storage;
import utility.TaskList;
import utility.Ui;

/**
 * {@link Command} to Exits application.
 */
public class ByeCommand extends Command {

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
     * Saves task to save file.
     *
     * @param taskList the {@link TaskList} of which to be stored to disk.
     * @param storage the storage object which the {@link TaskList} will be saved to.
     * @return taskList with no modification.
     */
    @Override
    public TaskList runCommand(TaskList taskList, Storage storage) {
        Ui.say("Bye. Hope to see you again soon!\n");
        storage.saveTaskList(taskList);
        return taskList;
    }

}
