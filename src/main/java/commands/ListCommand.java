package commands;

import task.TaskList;

/**
 * Represents a command to display tasks in specified TaskList
 * This class extends the {@link Command} class and handles displaying of tasks
 */
public class ListCommand extends Command{
    /**
     * Executes the list command, displaying all tasks currently in the task list.
     * This method calls the {@code displayList} method of the {@link TaskList} class to print the list of tasks.
     *
     * @param taskList The task list whose tasks will be displayed.
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.displayList();
    }
}
