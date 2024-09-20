package johncena.commands;

import java.util.ArrayList;

import johncena.exceptions.CenaInvalidTaskIndexException;
import johncena.storage.Storage;
import johncena.tasks.Task;

/**
 * The {@code UnmarkCommand} class implements the {@code Command} interface and provides
 * the functionality to execute the "unmark" command, which marks a task as not done in the task list.
 */
public class UnmarkCommand implements Command{
    private ArrayList<Task> tasks;
    private int taskIndex;

    /**
     * Constructs a new {@code UnmarkCommand} with the specified task list and task index.
     *
     * @param tasks the list of tasks
     * @param taskIndex the index of the task to be marked as not done
     */
    public UnmarkCommand(ArrayList<Task> tasks, int taskIndex) {
        this.tasks = tasks;
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the "unmark" command. Marks a task as not done in the task list.
     *
     * @throws CenaInvalidTaskIndexException if the task index is invalid
     */
    @Override
    public void execute() throws CenaInvalidTaskIndexException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new CenaInvalidTaskIndexException("The task index is invalid.");
        }
        tasks.get(taskIndex).markAsNotDone();
        Storage.saveTasks(tasks);
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + tasks.get(taskIndex));
        System.out.println("____________________________________________________________");
    }

}
