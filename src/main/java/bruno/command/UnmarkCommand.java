package bruno.command;

import bruno.Ui;
import bruno.exceptions.BrunoException;
import bruno.task.Task;
import bruno.task.TaskList;

import java.util.ArrayList;

/**
 * Represents a command to unmark a specific task as incomplete.
 * This command updates the status of a task in the task list to indicate that it is not done.
 */
public class UnmarkCommand extends Command {
    private String[] taskNums;
    private ArrayList<Task> unmarkedTasks;

    /**
     * Constructs a UnmarkCommand with the specified task list and task number.
     *
     * @param taskList   The task list in which the task status will be updated.
     * @param taskNums The indices of the tasks to be unmarked as incomplete (1-based index).
     */
    public UnmarkCommand(TaskList taskList, String ... taskNums) {
        super(taskList);
        this.taskNums = taskNums;
    }

    /**
     * Executes the command by unmarking the specified task as incomplete.
     * If an error occurs during the process, an error message is printed.
     */
    @Override
    public void execute() throws BrunoException {
        unmarkedTasks = getTaskList().unmarkTask(taskNums);
    }

    @Override
    public String toString() {
        String tasksAsString = "";
        for (Task task : unmarkedTasks) {
            tasksAsString += task + "\n";
        }
        return "I've unmarked these tasks as done:\n" + tasksAsString;
    }
}
