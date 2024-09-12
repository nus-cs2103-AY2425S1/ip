package choaticbot.actions;

import choaticbot.tasks.TaskList;

/**
 * The {@code List} class represents an action that lists all tasks in the task list.
 * It extends the {@link Action} class and provides an implementation to display the
 * entire task list.
 */
public class List extends Action {

    /**
     * Constructs a {@code List} action with the specified task list.
     *
     * @param taskList The task list to be displayed.
     */
    public List(TaskList taskList) {
        super(taskList);
    }

    /**
     * Executes the list action. It calls the {@code listTask()} method on the task list
     * to display all tasks.
     */
    @Override
    public void execute() {
        this.taskList.listTask();
    }
}
