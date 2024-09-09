package strand.command;

import strand.Storage;
import strand.TaskList;
import strand.Ui;
import strand.exception.StrandException;
import strand.task.Deadline;
import strand.task.Event;
import strand.task.Task;
import strand.task.Todo;

/**
 * The {@code AddCommand} class represents a command to add a new task to the list.
 */
public class AddCommand extends Command {
    private final Task newTask;

    /**
     * Constructs an {@code AddCommand} for a {@code Todo} task.
     *
     * @param description The description of the {@code Todo} task.
     * @throws StrandException If the description is invalid.
     */
    public AddCommand(String description) throws StrandException {
        this.newTask = new Todo(description);
    }

    /**
     * Constructs an {@code AddCommand} for a {@code Deadline} task.
     *
     * @param description The description of the {@code Deadline} task.
     * @param deadline    The deadline for the task.
     * @throws StrandException If the description or deadline is invalid.
     */
    public AddCommand(String description, String deadline) throws StrandException {
        this.newTask = new Deadline(description, deadline);
    }

    /**
     * Constructs an {@code AddCommand} for an {@code Event} task.
     *
     * @param description The description of the {@code Event} task.
     * @param start       The start time of the event.
     * @param end         The end time of the event.
     * @throws StrandException If the description, start, or end time is invalid.
     */
    public AddCommand(String description, String start, String end) throws StrandException {
        this.newTask = new Event(description, start, end);
    }

    /**
     * Executes the add command by adding the new task to the task list, updating the UI,
     * and saving the updated task list to storage.
     *
     * @param tasks   The current list of tasks to which the new task will be added.
     * @param ui      The {@code Ui} object used to interact with the user and show task updates.
     * @param storage The {@code Storage} object used to save the updated list of tasks.
     * @throws StrandException If there is an error during task addition, UI update, or storage operation.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws StrandException {
        tasks.addTask(this.newTask);
        String output = ui.taskAdded(this.newTask, tasks.getNumOfTasks());
        storage.save(tasks.convertToFileFormat());
        return output;
    }
}
