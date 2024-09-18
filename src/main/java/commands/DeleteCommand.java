package commands;

import task.Task;
import task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 * This command removes a task at the specified index and updates the task list.
 * If the index provided is invalid, an error message is displayed.
 * After successful deletion, the task list is saved to storage.
 */
public class DeleteCommand extends Command {
    private int indexToDelete;

    /**
     * Constructs a {@code DeleteCommand} with the specified index of the task to delete.
     *
     * @param indexToDelete The index of the task to be deleted (0-based index).
     */
    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    /**
     * Executes the delete task command, removing the task at the specified index from the task list.
     * If the index is out of bounds, an error message is displayed.
     *
     * @param taskList The task list from which the task will be removed.
     */
    @Override
    public String execute(TaskList taskList) {
        StringBuilder result = new StringBuilder();

        try {
            Task removedTask = taskList.removeTask(indexToDelete);

            result.append("----------------\n")
                    .append("WOOHOO! The following task has been ELIMINATED:\n ")
                    .append(removedTask).append("\n")
                    .append("HUH you still have ").append(taskList.getSize()).append(" tasks remaining??\n")
                    .append("----------------\n");

            taskList.writeToStorage();

        } catch (IndexOutOfBoundsException e) {
            result.append("No valid index was given!!");
        }

        return result.toString();
    }
}
