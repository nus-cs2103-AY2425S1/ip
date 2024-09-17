package bob.command;

import java.io.IOException;

import bob.Storage;
import bob.TaskList;
import bob.task.Task;

/**
 * Represents a command used to unmark tasks.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Creates a command object that operates on task i of the tasklist.
     *
     * @param taskIndex index of task.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            Task task = tasks.getTask(taskIndex);
            task.unmark();
            storage.save(tasks);
            return String.format("Oh well, this task has been marked undone:\n\t%s", task);
        } catch (IndexOutOfBoundsException e) {
            return "There's no such task!";
        } catch (IOException e) {
            return "I can't remember that for some reason T T";
        }
    }
}