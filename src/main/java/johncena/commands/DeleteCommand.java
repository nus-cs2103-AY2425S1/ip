package johncena.commands;

import johncena.exceptions.CenaInvalidTaskIndexException;
import johncena.storage.Storage;
import johncena.tasks.Task;

import java.util.ArrayList;


/**
 * The {@code DeleteCommand} class implements the {@code Command} interface and provides
 * the functionality to execute the "delete" command, which removes a task from the task list.
 */
public class DeleteCommand implements Command {
    private ArrayList<Task> tasks;
    private int taskIndex;

    public DeleteCommand(ArrayList<Task> tasks, int taskIndex) {
        this.tasks = tasks;
        this.taskIndex = taskIndex;
    }


    /**
     * Executes the "delete" command. Removes a task from the task list.
     *
     * @throws CenaInvalidTaskIndexException if the task index is invalid
     */
    @Override
    public void execute() throws CenaInvalidTaskIndexException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new CenaInvalidTaskIndexException("The task index is invalid.");
        }
        Task removedTask = tasks.remove(taskIndex);
        Storage.saveTasks(tasks);
        System.out.println("____________________________________________________________");
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + removedTask);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");

    }
}
