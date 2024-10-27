package controllers.commands;

import controllers.OutputHandler;
import models.TaskList;

/**
 * Represents a command to mark a task as completed in the task management system.
 * The {@code MarkTaskCommand} class implements the {@code Command} interface and
 * marks a task at the specified index in the {@code TaskList} as completed.
 *
 * <p>This command attempts to mark a task as done and handles any errors related
 * to invalid task indices.</p>
 */
public class MarkTaskCommand implements Command {
    private int index;

    /**
     * Constructs a {@code MarkTaskCommand} with the specified task index.
     *
     * @param index The index of the task to mark as completed (1-based index).
     */
    public MarkTaskCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark the task at the specified index in the {@code TaskList} as completed.
     * If the index is valid, the task is marked as done, and the task details are printed.
     * If the index is out of bounds, an error message is printed.
     *
     * @param taskList The {@code TaskList} on which the command operates.
     */
    @Override
    public void execute(TaskList taskList, OutputHandler outputHandler) {
        assert taskList != null : "taskList must not be null";
        assert outputHandler != null : "outputHandler must not be null";
        try {
            taskList.markTask(index - 1);
            outputHandler.print("____________________________________________________________");
            outputHandler.print("Nice! I've marked this task as done:");
            outputHandler.print(taskList.getTask(this.index - 1).toString());
            outputHandler.print("____________________________________________________________");
        } catch (IndexOutOfBoundsException e) {
            outputHandler.print("index out of bounds");
        }
    }
}
