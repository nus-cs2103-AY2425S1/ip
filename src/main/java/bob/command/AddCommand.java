package bob.command;

import java.io.IOException;

import bob.Storage;
import bob.TaskList;
import bob.task.Task;

/**
 * Represents a command that adds a task.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Returns an AddCommand object to be executed.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command, adding the task.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            tasks.add(task);
            storage.save(tasks);
            return (
                    String.format("Here's the added task:\n" +
                            "\t%s\n" +
                            "Now you have %s tasks in the list.", task, tasks.size())
            );
        } catch (IOException e) {
            return "I can't remember that for some reason T T";
        }
    }
}

