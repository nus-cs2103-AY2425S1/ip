package Gary.command;

import java.io.IOException;
import Gary.TaskList;
import Gary.Ui;
import Gary.Storage;
import Gary.task.Task;

/**
 * Represents a command to delete a task from the task list in the Gary chatbot.
 */
public class DeleteCommand extends Command {
    private int deletedIndex;

    /**
     * Constructs a DeleteCommand with the index of the task to be deleted.
     *
     * @param deletedIndex The index of the task to be deleted in the task list.
     */
    public DeleteCommand(int deletedIndex) {
        this.deletedIndex = deletedIndex;
    }

    /**
     * Executes the delete command. Removes the task at the specified index from the task list,
     * displays a confirmation message, and saves the updated task list to storage.
     *
     * @param taskLists The task list from which a task will be removed.
     * @param ui The UI object for interacting with the user.
     * @param storage The storage object to save the updated task list.
     */
    @Override
    public void execute(TaskList taskLists, Ui ui, Storage storage) {
        try {
            Task deletedTask = taskLists.removeTask(deletedIndex);
            ui.deleteTask(deletedTask, taskLists.size());
            storage.saveTask(taskLists);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the task list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task list index is out of bounds!");
        }
    }

    /**
     * Indicates that the DeleteCommand does not terminate the application.
     *
     * @return false as the delete command does not terminate the application.
     */
    @Override
    public boolean isBye() {
        return false;
    }
}