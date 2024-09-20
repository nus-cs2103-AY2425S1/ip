package orion.commands;

import orion.exceptions.OrionInputException;
import orion.tasks.Task;
import orion.utils.Storage;
import orion.utils.TaskList;

/**
 * Represents a command to mark a task as completed in the task list.
 * <p>
 * This command marks the task at a specified position in the {@link TaskList}
 * as completed and then updates the user interface to reflect this change.
 * </p>
 */
public class MarkTaskCommand extends Command {

    private int taskNo;

    /**
     * Creates a {@code MarkTaskCommand} with the specified task number.
     *
     * @param command the array containing the command and task number (expected format: mark &lt;task number&gt;)
     * @throws OrionInputException if the input does not meet the expected format or if the task number
     *                             is not a valid integer
     */
    public MarkTaskCommand(String[] command) throws OrionInputException {
        super(false);
        if (command.length != 2) {
            throw new OrionInputException("Correct syntax: mark <task number>");
        } else {
            try {
                taskNo = Integer.parseInt(command[1]);
            } catch (NumberFormatException e) {
                throw new OrionInputException("Correct syntax: mark <task number>");
            }
        }
    }

    /**
     * Executes the mark command by marking the task as completed and updating
     * the user interface.
     * <p>
     * This method checks if the given task number is valid. It throws an
     * {@link OrionInputException} if the task number is less than 1 or greater
     * than the number of tasks in the list.
     * </p>
     *
     * @param tasks  the {@link TaskList} containing the tasks
     * @param storage the {@link Storage} for managing the task list (not used in this command)
     * @throws OrionInputException if the task number is invalid, or if the task is already marked
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws OrionInputException {
        if (taskNo < 1) {
            throw new OrionInputException("Please provide a positive task number!");
        }
        if (taskNo > tasks.getNoTasks()) {
            String errorMsg = String.format("Number of tasks: %d. Unable to mark task %d as completed.",
                    tasks.getNoTasks(), taskNo);
            throw new OrionInputException(errorMsg);
        }
        Task task = tasks.markTask(taskNo - 1);
        return String.format("Sure! I've marked the following task as done:\n"
                + task.toString());
    }
}
