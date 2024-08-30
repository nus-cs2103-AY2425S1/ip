package yoda.commands;

import yoda.TaskList;
import yoda.tasks.Task;

import java.util.ArrayList;

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
     */
    public void run() {
        System.out.println("Do or do not, there is no try.");
        Task[] taskArray = tasks.toArray(new Task[0]);
        for (int i = 0; i < taskArray.length; i++) {
            System.out.printf("%d. %s\n", i + 1, taskArray[i]);
        }
    }
}
