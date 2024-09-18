package bruno.command;

import bruno.Ui;
import bruno.task.Task;
import bruno.task.TaskList;

import java.util.ArrayList;

/**
 * Represents a command to list all tasks in the task list.
 * This command retrieves and displays all tasks currently stored in the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand with the specified task list.
     *
     * @param taskList The task list to be displayed.
     */
    public ListCommand(TaskList taskList) {
        super(taskList);
    }

    /**
     * Executes the command by printing the list of tasks.
     * This method retrieves the tasks from the task list and uses the UI to display them.
     */
    @Override
    public void execute() {

    }

    @Override
    public String toString() {
        ArrayList<Task> tasks = getTaskList().getTasks();
        String tasksAsString = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksAsString += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return "Here are the tasks stored in my brain:\n" + tasksAsString;
    }
}
