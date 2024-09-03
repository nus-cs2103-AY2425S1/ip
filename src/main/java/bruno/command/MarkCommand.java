package bruno.command;

import bruno.Ui;
import bruno.exceptions.BrunoException;
import bruno.task.Task;
import bruno.task.TaskList;

/**
 * Represents a command to mark a specific task as completed.
 * This command updates the status of a task in the task list to indicate that it is done.
 */
public class MarkCommand extends Command {
    private String taskNum;
    private Task task;

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
    public void execute() throws BrunoException {
        task = getTaskList().markTask(taskNum);
    }

    @Override
    public String toString() {
        return "Nice! I've marked this task as done:\n" + task;
    }
}
