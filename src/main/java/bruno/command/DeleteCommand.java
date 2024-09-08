package bruno.command;

import bruno.Ui;
import bruno.exceptions.BrunoException;
import bruno.task.Task;
import bruno.task.TaskList;

import java.util.ArrayList;

/**
 * Represents a command to delete a task from the task list.
 * This command encapsulates the information needed to remove a specific task from
 * the task list based on its index and handles the execution of the task deletion.
 */
public class DeleteCommand extends Command {
    private String[] taskNums;
    private ArrayList<Task> deletedTasks;

    /**
     * Constructs a DeleteCommand with the specified task list and task number.
     *
     * @param taskList   The task list from which the task will be deleted.
     * @param taskNums The indices of the tasks to be deleted (1-based index).
     */
    public DeleteCommand(TaskList taskList, String ... taskNums) {
        super(taskList);
        this.taskNums = taskNums;
    }

    /**
     * Executes the command by deleting the task with the specified index from the task list.
     * If an error occurs during task deletion, an error message is printed.
     */
    @Override
    public void execute() throws BrunoException {
        deletedTasks = getTaskList().deleteTask(taskNums);
    }

    @Override
    public String toString() {
        String tasksAsString = "";
        for (Task task : deletedTasks) {
            tasksAsString += task + "\n";
        }
        return "Noted! I've removed these tasks:\n" + tasksAsString
                + "Now you have " + getTaskList().getTasks().size() + " tasks in the list";
    }
}
