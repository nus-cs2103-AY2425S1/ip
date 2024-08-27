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
     * @param indexOfMark Integer index of element in the task list that wanted to mark.
     */
    public MarkCommand(int indexOfMark) {
        this.indexOfMark = indexOfMark;
    }

    /**
     * Run the command by marking the index of task in the task list as done.
     * @param taskList Task list which the task will be marked.
     * @param storage Storage used to save the marked task.
     * @param ui User interface that interacts with user.
     * @throws LamaException Thrown if an error occurs during execution of command.
     */
    @Override
    public void run(TaskList taskList, Storage storage, Ui ui) throws LamaException {
        try {
            taskList.get(indexOfMark).markAsDone();
            ui.showMarkCommand(taskList, indexOfMark);
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new LamaException("Sorry, the number given exceed the bound of list");
        }
    }

}
