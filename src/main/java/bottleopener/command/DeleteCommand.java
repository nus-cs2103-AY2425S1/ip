package bottleopener.command;

import bottleopener.task.Tasklist;
import bottleopener.ui.Ui;

/**
 * The {@code DeleteCommand} class handles the deletion of a task from the task list in the BottleOpener chatbot.
 * It extends {@code CommandWithIndex} to target a specific task by its index for deletion.
 */
public class DeleteCommand extends CommandWithIndex {

    /**
     * Constructs a {@code DeleteCommand} object with the specified task list and task index.
     *
     * @param tasklist The list of tasks from which a task will be deleted.
     * @param index The index of the task in the task list to be deleted.
     */
    public DeleteCommand(Tasklist tasklist, int index) {
        super(tasklist, index);
    }

    /**
     * Executes the delete command by removing the task at the specified index from the task list.
     * If the index is invalid, an appropriate error message is returned.
     *
     * @return A string indicating the result of the deletion, typically a confirmation message.
     * @throws IllegalArgumentException If the provided index is out of bounds, indicating an invalid task number.
     */
    @Override
    public String runCommand() throws IllegalArgumentException {
        try {
            tasklist.deleteTask(index);
            return Ui.showDelete();
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(Ui.showAppropriateNumberError());
        }

    }

}
