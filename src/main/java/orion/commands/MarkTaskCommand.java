package orion.commands;

import orion.exceptions.OrionException;
import orion.utils.Storage;
import orion.utils.TaskList;
import orion.exceptions.OrionInputException;
import orion.tasks.Task;

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
     * @param taskNo the number of the task to be marked as completed (1-based index)
     */
    public MarkTaskCommand(int taskNo) {
        super(false);
        this.taskNo = taskNo;
    }

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
     * Executes the command by marking the task as completed and updating
     * the user interface.
     * <p>
     * This method checks if the given task number is valid. It throws an
     * {@link OrionInputException} if the task number is less than 1 or greater
     * than the number of tasks in the list.
     * </p>
     *
     * @param tasks  the {@link TaskList} containing the tasks
     * @param storage the {@link Storage} for managing the task list
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
