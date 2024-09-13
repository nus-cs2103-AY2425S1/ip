package johncena.commands;

import johncena.exceptions.CenaInvalidDeadlineException;
import johncena.storage.Storage;
import johncena.tasks.Deadline;
import johncena.tasks.Task;

import java.util.ArrayList;

public class DeadlineCommand implements Command{

    private ArrayList<Task> tasks;
    private String description;
    private String by;

    public DeadlineCommand(ArrayList<Task> tasks, String description, String by) {
        this.tasks = tasks;
        this.description = description;
        this.by = by;
    }

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
