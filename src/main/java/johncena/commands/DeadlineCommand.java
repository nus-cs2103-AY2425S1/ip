package johncena.commands;

import java.util.ArrayList;

import johncena.exceptions.CenaInvalidDeadlineException;
import johncena.storage.Storage;
import johncena.tasks.Deadline;
import johncena.tasks.Task;

/**
 * The {@code DeadlineCommand} class implements the {@code Command} interface and provides
 * the functionality to execute the "deadline" command, which adds a deadline task to the task list.
 */
public class DeadlineCommand implements Command {

    private ArrayList<Task> tasks;
    private String description;
    private String by;

    /**
     * Constructs a new {@code DeadlineCommand} with the specified task list, description, and deadline.
     *
     * @param tasks the list of tasks
     * @param description the description of the deadline task
     * @param by the deadline for the task
     */
    public DeadlineCommand(ArrayList<Task> tasks, String description, String by) {
        this.tasks = tasks;
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the "deadline" command. Adds a deadline task to the task list.
     *
     * @throws CenaInvalidDeadlineException if the description for the deadline is incorrect
     */
    @Override
    public void execute() throws CenaInvalidDeadlineException {
        if (description.isEmpty() || by.isEmpty()) {
            throw new CenaInvalidDeadlineException("Incorrect description for deadline. It should contain only /by.");
        }
        Task task = new Deadline(description, by);
        tasks.add(task);
        Storage.saveTasks(tasks);
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");

    }
}
