package bruno.command;

import bruno.task.TaskList;
import bruno.Ui;
import bruno.exceptions.BrunoException;

/**
 * Represents a command to unmark a specific task as incomplete.
 * This command updates the status of a task in the task list to indicate that it is not done.
 */
public class UnmarkCommand extends Command {
    private String taskNum;

    /**
     * Constructs a UnmarkCommand with the specified task list and task number.
     *
     * @param tasks   The task list in which the task status will be updated.
     * @param taskNum The index of the task to be unmarked as incomplete (1-based index).
     */
    public UnmarkCommand(TaskList tasks, String taskNum) {
        super(tasks);
        this.taskNum = taskNum;
    }

    /**
     * Executes the command by unmarking the specified task as incomplete.
     * If an error occurs during the process, an error message is printed.
     */
    @Override
    public void execute() {
        try {
            getTasks().unmarkTask(taskNum);
        } catch (BrunoException e) {
            Ui.printErrorMessage(e);
        }
    }
}