package orion.commands;

import orion.exceptions.OrionInputException;
import orion.tasks.Task;
import orion.utils.Storage;
import orion.utils.TaskList;

/**
 * Represents a command to mark a task as not completed in the task list.
 * <p>
 * This command unmarks the task at a specified position in the {@link TaskList}
 * and then updates the user interface to reflect this change.
 * </p>
 */
public class UnmarkTaskCommand extends Command {

    private int taskNo;

    /**
     * Creates a {@code UnmarkTaskCommand} with the specified task number.
     *
     * @param command the array containing the command and task number (expected format: unmark &lt;task number&gt;)
     * @throws OrionInputException if the input does not meet the expected format or if the task number
     *                             is not a valid integer
     */
    public UnmarkTaskCommand(String[] command) throws OrionInputException {
        super(false);
        if (command.length != 2) {
            throw new OrionInputException("Correct syntax: unmark <task number>");
        } else {
            try {
                taskNo = Integer.parseInt(command[1]);
            } catch (NumberFormatException e) {
                throw new OrionInputException("Correct syntax: unmark <task number>");
            }
        }
    }

    /**
     * Executes the command by marking the task as not completed and updating
     * the user interface.
     * <p>
     * This method checks if the given task number is valid. It throws an
     * {@link OrionInputException} if the task number is less than 1 or greater
     * than the number of tasks in the list.
     * </p>
     *
     * @param tasks  the {@link TaskList} containing the tasks
     * @param storage the {@link Storage} for managing the task list (not used in this command)
     * @throws OrionInputException if the task number is invalid, or if the task is already unmarked
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws OrionInputException {
        if (taskNo < 1) {
            throw new OrionInputException("Please provide a positive task number!");
        }
        if (taskNo > tasks.getNoTasks()) {
            String errorMsg = String.format("Number of tasks: %d. Unable to mark task %d as uncompleted.",
                    tasks.getNoTasks(), taskNo);
            throw new OrionInputException(errorMsg);
        }
        Task task = tasks.unmarkTask(taskNo - 1);
        return String.format("Sure! I've marked the following task as undone:\n"
                + task.toString());
    }
}
