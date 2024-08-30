package lutodo.commands;

import lutodo.tasklist.TaskList;
import lutodo.storage.Storage;

/**
 * Represents the command of deleting a task in the task list.
 */
public class DeleteCommand extends Command{
    private int index;

    /**
     * Constructs a DeleteCommand object with the index of the task to be deleted.
     *
     * @param index The index of the task to be deleted. 1 represents the first task.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes a task in the task list, informs the user and saves the new task list.
     *
     * @param tasks   The TaskList that has some task to be deleted.
     * @param storage The Storage object used to save the new task list.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            System.out.println("Noted. I've removed this task:\n" + tasks.get(index)
                    + "\nNow you have " + (tasks.size() - 1) + " tasks in the list.");
            tasks.deleteTask(index);
        } catch(IndexOutOfBoundsException e) {
            System.out.println("The task you want to delete is not in task list, please try again.");
        }
        storage.save(tasks);
    }

    /**
     * Returns false since this type of command is not exit command.
     *
     * @return whether this is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Represents the command with a String containing basic information of this command.
     *
     * @return a String containing command type and the index of the task to be deleted.
     */
    @Override
    public String toString() {
        return "DeleteCommand: " + index;
    }
}
