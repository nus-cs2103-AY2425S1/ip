package shrimp.command;

import shrimp.task.Task;
import shrimp.task.TaskList;
import shrimp.utility.Ui;

/**
 * Represents a command to mark or unmark a task in the task list.
 */
public class MarkCommand implements Command {
    /** The index of the task list to be marked. */
    private final int index;
    /** The boolean value to signify to mark or unmark the task. */
    private final Boolean toMark;

    /**
     * Constructs a {@code MarkCommand} with the specified task index and mark status.
     *
     * @param index  The index of the task to be marked or unmarked.
     * @param toMark {@code true} if the task is to be marked as done, {@code false} otherwise.
     */
    public MarkCommand(int index, Boolean toMark) {
        this.index = index;
        this.toMark = toMark;
    }

    /**
     * Executes the mark command by marking or unmarking the task in the task list
     * and printing the result to the user interface.
     *
     * @param tasks The list of tasks containing the task to be marked or unmarked.
     * @param ui    The user interface to print the result of the command.
     */
    @Override
    public void run(TaskList tasks, Ui ui) {
        Task task;
        if (toMark) {
            task = tasks.getTask(index).markAsDone();
        } else {
            task = tasks.getTask(index).markAsNotDone();
        }
        tasks.replaceTask(index, task);
        ui.printMark(task);
    }
}
