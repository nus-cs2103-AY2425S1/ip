package johncena.commands;

import java.util.ArrayList;

import johncena.exceptions.CenaInvalidAfterException;
import johncena.storage.Storage;
import johncena.tasks.After;
import johncena.tasks.Task;

/**
 * The {@code AfterCommand} class implements the {@code Command} interface and provides
 * the functionality to execute the "after" command, which adds an after task to the task list.
 */
public class AfterCommand implements Command {

    private ArrayList<Task> tasks;
    private String description;
    private String after;

    /**
     * Constructs a new {@code AfterCommand} with the specified task list, description, and after time.
     *
     * @param tasks the list of tasks
     * @param description the description of the after task
     * @param after the time after which the task should be done
     */
    public AfterCommand(ArrayList<Task> tasks, String description, String after) {
        this.tasks = tasks;
        this.description = description;
        this.after = after;
    }

    /**
     * Executes the "after" command. Adds an after task to the task list.
     *
     * @throws CenaInvalidAfterException if the description for the after task is incorrect
     */
    @Override
    public String execute() {
        assert description != null : "Description should not be null";
        assert after != null : "After time should not be null";
        try {
            if (description.isEmpty() || after.isEmpty()) {
                throw new CenaInvalidAfterException("Incorrect description for after task. It should contain /after.");
            }
            Task task = new After(description, after);
            tasks.add(task);
            Storage.saveTasks(tasks);

            StringBuilder sb = new StringBuilder();
            sb.append(" Got it. I've added this task:\n");
            sb.append("   ").append(task).append("\n");
            sb.append(" Now you have ").append(tasks.size()).append(" tasks in the list.\n");
            return sb.toString();
        } catch (CenaInvalidAfterException e) {
            return e.getMessage();
        }
    }
}
