package myapp.command;

import myapp.core.Storage;
import myapp.task.FixedDuration;
import myapp.task.Task;
import myapp.task.TaskList;

/**
 * The {@code FixedDurationCommand} class handles the creation and addition of a {@link FixedDuration} task.
 * It extends the {@link AddCommand} and allows the user to create tasks that have a fixed duration but no fixed start or end time.
 */
public class FixedDurationCommand extends AddCommand {

    private int duration;

    /**
     * Constructs a {@code FixedDurationCommand} with the specified description and duration.
     *
     * @param description the description of the task to be added.
     * @param duration the duration of the task in hours.
     */
    public FixedDurationCommand(String description, int duration) {
        super(description);
        this.duration = duration;
    }

    /**
     * Executes the command to add a {@link FixedDuration} task to the provided {@link TaskList}.
     * The task is then saved using the provided {@link Storage} object.
     *
     * @param tasks the {@link TaskList} where the task will be added.
     * @param storage the {@link Storage} object to save the updated list of tasks.
     * @return a message indicating that the task was successfully added.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task task = new FixedDuration(description, duration);
        tasks.add(task);
        saveTasks(tasks, storage);
        return printAddMessage(tasks, task);
    }
}
