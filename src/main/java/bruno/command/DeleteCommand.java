package bruno.command;

import bruno.task.TaskList;
import bruno.Ui;
import bruno.exceptions.BrunoException;

/**
 * Represents a command to delete a task from the task list.
 * This command encapsulates the information needed to remove a specific task from
 * the task list based on its index and handles the execution of the task deletion.
 */
public class DeleteCommand extends Command {
    private String taskNum;

    /**
     * Constructs a DeleteCommand with the specified task list and task number.
     *
     * @param tasks   The task list from which the task will be deleted.
     * @param taskNum The index of the task to be deleted (1-based index).
     */
    public DeleteCommand(TaskList tasks, String taskNum) {
        super(tasks);
        this.taskNum = taskNum;
    }

    /**
     * Executes the command by deleting the task with the specified index from the task list.
     * If an error occurs during task deletion, an error message is printed.
     */
    @Override
    public void execute() {
        try {
            getTasks().deleteTask(taskNum);
        } catch (BrunoException e) {
            Ui.printErrorMessage(e);
        }
    }
}
