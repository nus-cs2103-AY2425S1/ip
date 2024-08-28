package bruno.command;

import bruno.task.TaskList;
import bruno.Ui;

/**
 * Represents a command to list all tasks in the task list.
 * This command retrieves and displays all tasks currently stored in the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand with the specified task list.
     *
     * @param tasks The task list to be displayed.
     */
    public ListCommand(TaskList tasks) {
        super(tasks);
    }

    /**
     * Executes the command by printing the list of tasks.
     * This method retrieves the tasks from the task list and uses the UI to display them.
     */
    @Override
    public void execute() {
        Ui.printList(getTasks().getTasks());
    }
}
