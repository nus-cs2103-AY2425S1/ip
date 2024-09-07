package controllers.commands;

import models.TaskList;

/**
 * Represents a command to list all tasks in the task management system.
 * The {@code ListCommand} class implements the {@code Command} interface and
 * displays all tasks in the {@code TaskList}.
 *
 * <p>This command retrieves and prints the current list of tasks.</p>
 */
public class ListCommand implements Command {

    /**
     * Executes the command to list all tasks in the {@code TaskList}.
     * It prints the task list to the console.
     *
     * @param taskList The {@code TaskList} from which tasks are retrieved and displayed.
     */
    @Override
    public void execute(TaskList taskList) {
        System.out.println(taskList.listTasks());
    }
}
