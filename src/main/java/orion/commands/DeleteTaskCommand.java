package orion.commands;

import orion.exceptions.OrionException;
import orion.exceptions.OrionInputException;
import orion.tasks.Task;
import orion.utils.Storage;
import orion.utils.TaskList;

/**
 * Represents a command to delete a task from the task list.
 * <p>
 * This command deletes the task at a specified position in the {@link TaskList}
 * and then updates the user interface to reflect this deletion.
 * </p>
 */
public class DeleteTaskCommand extends Command {

    private int taskNo;

    /**
     * Creates a {@code DeleteTaskCommand} with the specified task number.
     *
     * @param command the array containing the command and task number (expected format: delete &lt;task number&gt;)
     * @throws OrionInputException if the input does not meet the expected format or if the task number
     *                             is not a valid integer
     */
    public DeleteTaskCommand(String[] command) throws OrionInputException {
        super(false);
        if (command.length != 2) {
            throw new OrionInputException("Correct syntax: delete <task number>");
        } else {
            try {
                taskNo = Integer.parseInt(command[1]);
            } catch (NumberFormatException e) {
                throw new OrionInputException("Correct syntax: delete <task number>");
            }
        }
    }

    /**
     * Executes the command by deleting the task from the task list and updating
     * the user interface.
     * <p>
     * This method checks if the given task number is valid. It throws an
     * {@link OrionInputException} if the task number is less than 1 or greater
     * than the number of tasks in the list.
     * </p>
     *
     * @param tasks  the {@link TaskList} from which the task will be deleted
     * @param storage the {@link Storage} for managing the task list
     * @throws OrionException if an error occurs during execution, such as
     *                        an invalid task number
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws OrionException {
        if (taskNo < 1) {
            throw new OrionInputException("Please provide a positive task number!");
        }
        if (taskNo > tasks.getNoTasks()) {
            String errorMsg = String.format("Number of tasks: %d. Unable to delete task %d.",
                    tasks.getNoTasks(), taskNo);
            throw new OrionInputException(errorMsg);
        }
        Task task = tasks.deleteTask(taskNo - 1);
        return String.format("Sure! I've deleted the following task:\n"
                + task.toString() + "\n"
                + "Now you have " + tasks.getNoTasks() + " tasks in your list.");
    }
}

