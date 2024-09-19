package pixel.command;

import pixel.PixelException;
import pixel.Storage;
import pixel.Ui;
import pixel.task.Deadline;
import pixel.task.Event;
import pixel.task.Task;
import pixel.task.TaskList;
import pixel.task.Todo;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand object for adding a deadline task.
     *
     * @param deadline The deadline task to be added.
     */
    public AddCommand(Deadline deadline) {
        super(false);
        this.task = deadline;
    }

    /**
     * Constructs an AddCommand object for adding a todo task.
     *
     * @param todo The todo task to be added.
     */
    public AddCommand(Todo todo) {
        super(false);
        this.task = todo;
    }

    /**
     * Constructs an AddCommand object for adding an event task.
     *
     * @param event The event task to be added.
     */
    public AddCommand(Event event) {
        super(false);
        this.task = event;
    }

    /**
     * Executes the add command by adding the task to the task list.
     *
     * @param taskList The task list to add the task to.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving the task list.
     * @throws PixelException If there is an error executing the command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PixelException {
        taskList.addTask(this.task);
        ui.pixelSays("Got it. I've added this task:", "  " + this.task, "Now you have " + taskList.size()
                + " tasks in the list.");
    }

    /**
     * Executes the add command by adding the task to the task list and returns a
     * response.
     *
     * @param taskList The task list to add the task to.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving the task list.
     * @return The response to be displayed to the user.
     * @throws PixelException If there is an error executing the command.
     */
    @Override
    public String executeAndGetResponse(TaskList taskList, Ui ui, Storage storage) throws PixelException {
        taskList.addTask(this.task);
        return "Got it. I've added this task:\n" + "  " + this.task + "\n" + "Now you have " + taskList.size()
                + " tasks in the list.";
    }
}
