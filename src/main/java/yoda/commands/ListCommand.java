package yoda.commands;

import java.util.ArrayList;

import yoda.TaskList;
import yoda.tasks.Task;

/**
 * Represents a command to display list of current tasks.
 */
public class ListCommand extends Command {
    protected ArrayList<Task> tasks;

    /**
     * Constructs a ListCommand with the task list to be displayed.
     *
     * @param tasks Task list to be displayed.
     */
    public ListCommand(TaskList tasks) {
        this.tasks = tasks.getTasks();
    }

    /**
     * Executes the ListCommand and displays the task list.
     *
     * @return
     */
    public String run() {
        StringBuilder message = new StringBuilder();
        message.append("Do or do not, there is no try.\n");
        Task[] taskArray = tasks.toArray(new Task[0]);
        for (int i = 0; i < taskArray.length; i++) {
            message.append(i + 1).append(". ").append(taskArray[i]).append("\n");
        }
        return message.toString();
    }

}
