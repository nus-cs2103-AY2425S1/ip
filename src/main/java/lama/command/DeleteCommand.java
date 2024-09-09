package lama.command;

import lama.LamaException;
import lama.Storage;
import lama.TaskList;
import lama.Ui;
import lama.task.Task;

/**
 * Represent a command to delete a task from the task list.
 * Subclass of command class.
 */
public class DeleteCommand extends Command {

    private int indexOfDeleteCommand;

    /**
     * Construct a DeleteCommand with specified index given.
     *
     * @param indexOfDeleteCommand Integer index of element that will be deleted in the task list.
     */
    public DeleteCommand(int indexOfDeleteCommand) {
        this.indexOfDeleteCommand = indexOfDeleteCommand;
    }

    /**
     * Run the command by deleting the index of task from the task list given.
     *
     * @param taskList Task list which the task will be deleted.
     * @param storage Storage used to save the new task list after deletion.
     * @param ui User interface that interacts with user.
     * @throws LamaException Thrown if an error occurs during execution of command.
     */
    @Override
    public String run(TaskList taskList, Storage storage, Ui ui) throws LamaException {
        assert taskList != null : "Task list should not be null";
        assert storage != null : "Storage should not be null";
        assert ui != null : "UI should not be null";

        try {
            Task task = taskList.remove(indexOfDeleteCommand);
            ui.showDeleteCommandHeader();
            System.out.println("  " + task);
            ui.showDeleteCommandFooter(taskList);
            storage.saveTasks(taskList);
            String output = "Noted. I've removed this task:\n"
                    + "  " + task + "\n"
                    + "Now you have " + taskList.size() + " tasks in the list.";
            return output;

        } catch (IndexOutOfBoundsException e) {
            throw new LamaException("Sorry, the number given exceed the bound of list");
        }
    }
}
