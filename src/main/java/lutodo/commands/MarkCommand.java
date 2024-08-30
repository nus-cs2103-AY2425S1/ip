package lutodo.commands;

import lutodo.tasklist.TaskList;
import lutodo.storage.Storage;

/**
 * Represents the command of mark/unmark a task in the task list.
 */
public class MarkCommand extends Command{
    private boolean isDone;
    private int index;

    /**
     * Constructs an MarkCommand object with the index of task that needs mark/unmark.
     *
     * @param index The index of the task to be marked. 1 represents the first task.
     * @param isDone Shows whether the task should be marked as done or not done.
     */
    public MarkCommand(int index, boolean isDone) {
        this.isDone = isDone;
        this.index = index;
    }

    /**
     * Marks the certain task as done / not done.
     *
     * @param tasks   The TaskList the method interacts with.
     * @param storage The Storage object used to save the new task list.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        if (isDone) {
            tasks.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:\n"
                    + tasks.get(index));
        } else {
            tasks.get(index).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:\n"
                    + tasks.get(index));
        }
        storage.save(tasks);
    }

    /**
     * Returns false since this type of command is not exit command.
     *
     * @return whether this is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Represents the command with a String containing basic information of this command.
     *
     * @return a String containing basic information of this command.
     */
    @Override
    public String toString() {
        return "MarkCommand: " + isDone;
    }
}
