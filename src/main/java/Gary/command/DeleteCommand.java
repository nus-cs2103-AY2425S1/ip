package Gary.command;

import java.io.IOException;
import Gary.TaskList;
import Gary.Ui;
import Gary.Storage;
import Gary.task.Task;

/**
 * The {@code DeleteCommand} class represents a command to delete a task from the task list.
 * It extends the {@code Command} class and implements the behavior for deleting a task.
 */
public class DeleteCommand extends Command {

    private int deletedIndex;  // The index of the task to be deleted

    /**
     * Constructs a {@code DeleteCommand} object with the specified index of the task to be deleted.
     *
     * @param deletedIndex The index of the task to delete.
     */
    public DeleteCommand(int deletedIndex) {
        this.deletedIndex = deletedIndex;
    }

    /**
     * Executes the delete command, which removes a task from the {@code TaskList},
     * updates the user through {@code Ui}, and saves the updated task list to storage.
     *
     * @param taskList The {@code TaskList} object containing tasks to be manipulated.
     * @param ui The {@code Ui} object for user interaction, used to display messages.
     * @param storage The {@code Storage} object for saving and loading tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task deletedTask = taskList.removeTask(deletedIndex);
            ui.deleteTask(deletedTask, taskList.size());
            storage.saveTask(taskList);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the task list: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task list index is out of bounds!");
        }
    }

    /**
     * Indicates that this command is not a "bye" command.
     *
     * @return {@code false} as this is not a "bye" command.
     */
    @Override
    public boolean isBye() {
        return false;
    }
}
