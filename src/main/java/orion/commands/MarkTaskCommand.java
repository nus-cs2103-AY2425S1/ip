package orion.commands;

import orion.chatbot.Storage;
import orion.chatbot.TaskList;
import orion.chatbot.Ui;
import orion.tasks.Task;
import orion.exceptions.OrionInputException;

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
     * @param ui      the {@link Ui} for updating the user interface
     * @throws OrionInputException if the task number is invalid, or if the task is already marked
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws OrionInputException {
        if (taskNo < 1) {
            throw new OrionInputException("Please provide a positive task number!");
        }
        if (taskNo > tasks.getNoTasks()) {
            String errorMsg = String.format("Number of tasks: %d. Unable to mark task %d as completed.",
                    tasks.getNoTasks(), taskNo);
            throw new OrionInputException(errorMsg);
        }
        Task task = tasks.markTask(taskNo - 1);
        ui.printMarkTask(task);
    }
}
