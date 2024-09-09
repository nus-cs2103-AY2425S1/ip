package lama.command;

import lama.LamaException;
import lama.Storage;
import lama.TaskList;
import lama.Ui;

/**
 * Represent a command to mark task in the task list as done.
 * Subclass of command.
 */
public class MarkCommand extends Command {

    private int indexOfMark;

    /**
     * Construct a MarkCommand with specified index.
     *
     * @param indexOfMark Integer index of element in the task list that wanted to mark.
     */
    public MarkCommand(int indexOfMark) {
        this.indexOfMark = indexOfMark;
    }

    /**
     * Run the command by marking the index of task in the task list as done.
     *
     * @param taskList Task list which the task will be marked.
     * @param storage Storage used to save the marked task.
     * @param ui User interface that interacts with user.
     * @throws LamaException Thrown if an error occurs during execution of command.
     */
    @Override
    public String run(TaskList taskList, Storage storage, Ui ui) throws LamaException {
        assert taskList != null : "Task list should not be null";
        assert storage != null : "Storage should not be null";
        assert ui != null : "UI should not be null";

        try {
            taskList.get(indexOfMark).markAsDone();
            ui.showMarkCommand(taskList, indexOfMark);
            storage.saveTasks(taskList);
            return "Nice! I've marked this task as done:\n"
                    + "  " + taskList.get(indexOfMark);
        } catch (IndexOutOfBoundsException e) {
            throw new LamaException("Sorry, the number given exceed the bound of list");
        }
    }

}
