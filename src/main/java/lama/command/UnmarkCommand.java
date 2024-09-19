package lama.command;

import lama.LamaException;
import lama.Storage;
import lama.TaskList;
import lama.Ui;

/**
 * Represent a command to unmark task in the task list.
 * Subclass of command.
 */
public class UnmarkCommand extends Command {

    private int indexOfUnmark;

    /**
     * Construct UnmarkCommand with specified index.
     *
     * @param indexOfUnmark Integer index of element in the task list that wanted to be unmarked.
     */
    public UnmarkCommand(int indexOfUnmark) {
        this.indexOfUnmark = indexOfUnmark;
    }

    /**
     * Run the command by making the index of task in the task list as not done.
     *
     * @param taskList Task list which the task will be unmarked.
     * @param storage Storage used to save latest task list.
     * @param ui User interface that interacts with user.
     * @throws LamaException Thrown if an error occurs during execution of command.
     */
    @Override
    public String run(TaskList taskList, Storage storage, Ui ui) throws LamaException {
        assert taskList != null : "Task list should not be null";
        assert storage != null : "Storage should not be null";
        assert ui != null : "UI should not be null";

        try {
            taskList.get(indexOfUnmark).markAsUnDone();
            ui.showUnmarkCommand(taskList, indexOfUnmark);
            storage.saveTasks(taskList);
            return "OK, I've marked this task as not done yet:\n"
                    + "  " + taskList.get(indexOfUnmark);
        } catch (IndexOutOfBoundsException e) {
            throw new LamaException("Sorry, the number given exceed the bound of list");
        }
    }

}
