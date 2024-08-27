package lama.command;

import lama.LamaException;
import lama.Storage;
import lama.TaskList;
import lama.Ui;

/**
 * Represents a command to list the task list.
 * Subclass of command class.
 */
public class ListCommand extends Command {

    /**
     * Run the command to show the list of task list.
     * @param taskList Task list being shown.
     * @param storage Storage used to save or load task, although not used in this command.
     * @param ui User interface that interacts with user.
     * @throws LamaException Thrown if an error occurs during the execution of the command.
     */
    @Override
    public void run(TaskList taskList, Storage storage, Ui ui) throws LamaException {
        ui.showListCommand(taskList);
    }

}
