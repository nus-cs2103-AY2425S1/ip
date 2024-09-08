package bruno.command;

import bruno.Ui;
import bruno.exceptions.BrunoException;
import bruno.task.Task;
import bruno.task.TaskList;

import java.util.ArrayList;

/**
 * Represents a command to mark a specific task as completed.
 * This command updates the status of a task in the task list to indicate that it is done.
 */
public class MarkCommand extends Command {
    private String[] taskNums;
    private ArrayList<Task> markedTasks;

    /**
     * Constructs a MarkCommand with the specified task list and task number.
     *
     * @param taskList The task list in which the task status will be updated.
     * @param taskNums The indices of the tasks to be marked as completed (1-based index).
     */
    public MarkCommand(TaskList taskList, String ... taskNums) {
        super(taskList);
        this.taskNums = taskNums;
    }

    /**
     * Executes the command by marking the specified task as completed.
     * If an error occurs during the process, an error message is printed.
     */
    @Override
    public void execute() throws BrunoException {
        markedTasks = getTaskList().markTask(taskNums);
    }

    @Override
    public String toString() {
        String tasksAsString = "";
        for (Task task : markedTasks) {
            tasksAsString += task + "\n";
        }
        return "Nice! I've marked these tasks as done:\n" + tasksAsString;
    }
}
