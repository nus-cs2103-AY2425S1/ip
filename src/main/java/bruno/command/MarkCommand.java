package bruno.command;

import bruno.task.TaskList;
import bruno.Ui;
import bruno.exceptions.BrunoException;

/**
 * Represents a command to mark a specific task as completed.
 * This command updates the status of a task in the task list to indicate that it is done.
 */
public class MarkCommand extends Command {
    private String taskNum;

    /**
     * Constructs a MarkCommand with the specified task list and task number.
     *
     * @param tasks   The task list in which the task status will be updated.
     * @param taskNum The index of the task to be marked as completed (1-based index).
     */
    public MarkCommand(TaskList tasks, String taskNum) {
        super(tasks);
        this.taskNum = taskNum;

    }

    /**
     * Executes the command by marking the specified task as completed.
     * If an error occurs during the process, an error message is printed.
     */
    @Override
    public void execute() {
        try {
            getTasks().markTask(taskNum);
        } catch (BrunoException e) {
            Ui.printErrorMessage(e);
        }
    }
}
