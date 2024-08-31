package commands;

import task.Task;
import task.TaskList;


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
    public void execute(TaskList taskList) {
        try {
            Task removedTask = taskList.removeTask(indexToDelete);
            System.out.println("----------------\n" +
                    "WOOHOO! The following task has been ELIMINATED:\n "
                    + removedTask + "\n"
                    + "HUH you still have " + taskList.getSize() + " tasks remaining??\n"
                    + "----------------\n");
            taskList.writeToStorage();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No valid index was given!!");
        }
    }
}