package morgana.commands;

import morgana.exceptions.MorganaException;
import morgana.storage.Storage;
import morgana.task.Task;
import morgana.task.TaskList;
import morgana.ui.Ui;

/**
 * Represents an abstract command to add a task to the task list.
 * Concrete subclasses should implement the {@link #createTask(String)} method
 * to define how the task is created based on the user's input.
 */
public abstract class AddCommand extends Command {
    private final String args;

    /**
     * Constructs an {@code AddCommand} with the specified arguments.
     *
     * @param args The string containing the task details to be added.
     */
    public AddCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MorganaException {
        Task task = createTask(args);
        tasks.add(task);
        ui.showToUser(
                "Got it. I've added this task:",
                task.toString(),
                "Now you have %d task%s in the list.".formatted(tasks.size(),
                        tasks.size() > 1 ? "s" : ""));
        storage.save(tasks);
    }

    /**
     * Creates a task based on the provided arguments.
     *
     * @param args The string containing the task details.
     * @return The created {@code Task}.
     * @throws MorganaException If an error occurs while creating the task.
     */
    abstract Task createTask(String args) throws MorganaException;
}
