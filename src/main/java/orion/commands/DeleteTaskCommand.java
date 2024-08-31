package orion.commands;

import orion.chatbot.Storage;
import orion.chatbot.TaskList;
import orion.chatbot.Ui;
import orion.exceptions.OrionException;
import orion.exceptions.OrionInputException;
import orion.tasks.Task;

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
     * @param taskNo the number of the task to be deleted (1-based index)
     */
    public DeleteTaskCommand(int taskNo) {
        super(false);
        this.taskNo = taskNo;
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
     * @param ui      the {@link Ui} for updating the user interface
     * @throws OrionException if an error occurs during execution, such as
     *                        an invalid task number
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws OrionException {
        if (taskNo < 1) {
            throw new OrionInputException("Please provide a positive task number!");
        }
        if (taskNo > tasks.getNoTasks()) {
            String errorMsg = String.format("Number of tasks: %d. Unable to delete task %d.",
                    tasks.getNoTasks(), taskNo);
            throw new OrionInputException(errorMsg);
        }
        Task task = tasks.deleteTask(taskNo - 1);
        ui.printDeleteTask(tasks, task);
    }
}

