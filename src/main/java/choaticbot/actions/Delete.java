package choaticbot.actions;

import choaticbot.tasks.TaskList;

/**
 * The {@code Delete} class represents an action that deletes a task from the task list.
 * It extends the {@link Action} class and provides an implementation to remove a task
 * based on its index.
 */
public class Delete extends Action {

    /**
     * The index of the task to be deleted, provided as a string.
     */
    private String details;

    /**
     * Constructs a {@code Delete} action with the specified task list and task index.
     *
     * @param taskList The task list from which the task will be deleted.
     * @param details The string containing the index of the task to delete.
     */
    public Delete(TaskList taskList, String details) {
        super(taskList);
        this.details = details;
    }

    /**
     * Executes the delete action. It parses the task index from the {@code details}
     * field and deletes the task at that index from the task list.
     */
    @Override
    public void execute() {
        int index = Integer.parseInt(this.details);
        this.taskList.deleteTask(index);
    }
}
