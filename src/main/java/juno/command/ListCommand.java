package juno.command;

import java.util.ArrayList;

import juno.manager.TaskManager;
import juno.manager.exception.TaskManagerException;
import juno.task.Task;

/**
 * A class to list all tasks currently in the list.
 * Retrieves and displays a formatted list of tasks currently managed
 * by the TaskManager.
 */
public class ListCommand extends Command {
    private ArrayList<Task> tasks;

    /**
     * Constructs a ListCommand instance which takes in a TaskManager instance.
     * Initialises the task list from the TaskManager by calling <code>getTasksArray()</code> method.
     *
     * @param taskManager The TaskManager instance to retrieve the tasks from.
     */
    public ListCommand(TaskManager taskManager) {
        this.tasks = taskManager.getTasksArray();
    }

    /**
     * Executes the command to list all tasks that the user has now.
     * If the task list is empty, throws a TaskManagerException.
     * Otherwise, prints each task in a formatted list and the total
     * number of tasks.
     * Can be executed with the "list" input prompt.
     *
     * @throws TaskManagerException If no tasks are present in the list.
     */
    @Override
    public String runCommand() throws TaskManagerException {
        if (this.tasks.isEmpty()) {
            throw new TaskManagerException("\uD83C\uDF31 No tasks added yet! "
                    + "Why not plant the first seed? \uD83C\uDF31", TaskManagerException.ErrorType.EMPTY_LIST);
        }

        StringBuilder outString = new StringBuilder("Here's a rundown of all your tasks! \uD83D\uDE0A");
        for (int i = 0; i < this.tasks.size(); i++) {
            String formmattedString = String.format(
                    "%d. %s", (i + 1),
                    this.tasks.get(i).toString()
            );
            outString.append("\n").append(formmattedString);
        }
        outString.append("\n")
                 .append("\uD83C\uDFAF You have ")
                 .append(this.tasks.size())
                 .append(" tasks in the list. Keep going!");
        return outString.toString();
    }
}
