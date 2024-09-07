package controllers.commands;

import models.TaskList;

/**
 * Represents a command to unmark a task as not completed in the task management system.
 * The {@code UnmarkTaskCommand} class implements the {@code Command} interface and
 * unmarks a task at the specified index in the {@code TaskList}.
 *
 * <p>This command attempts to mark a task as not completed and handles any errors
 * related to invalid task indices.</p>
 */
public class UnmarkTaskCommand implements Command {
    private int index;

    /**
     * Constructs an {@code UnmarkTaskCommand} with the specified task index.
     *
     * @param index The index of the task to unmark (1-based index).
     */
    public UnmarkTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to unmark the task at the specified index in the {@code TaskList}.
     * If the index is valid, the task is marked as not done, and the task details are printed.
     * If the index is out of bounds, an error message is printed.
     *
     * @param taskList The {@code TaskList} on which the command operates.
     */
    @Override
    public void execute(TaskList taskList) {
        try {
            taskList.unmarkTask(index - 1);
            System.out.println("____________________________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(taskList.getTask(this.index - 1));
            System.out.println("____________________________________________________________");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("index out of bounds");
        }
    }
}
