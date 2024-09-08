package bruno.command;

import bruno.Bruno;
import bruno.Ui;
import bruno.exceptions.BrunoException;
import bruno.task.Task;
import bruno.task.TaskList;

/**
 * Represents a command to add a new task to the task list.
 * This command encapsulates the information needed to add a task of a specified type
 * to the task list and handles the execution of the task addition.
 */
public class AddCommand extends Command {
    private String description;
    private Bruno.TaskType type;
    private Task task;

    /**
     * Constructs an AddCommand with the specified task list, description, and task type.
     *
     * @param taskList        The task list to which the new task will be added.
     * @param description  The description of the task to be added.
     * @param type         The type of the task to be added.
     */
    public AddCommand(TaskList taskList, String description, Bruno.TaskType type) {
        super(taskList);
        this.description = description;
        this.type = type;
    }

    /**
     * Executes the command by adding the new task with the specified description and type
     * to the task list. If an error occurs during task addition, an error message is printed.
     */
    @Override
    public void execute() throws BrunoException {
        task = getTaskList().addTask(description, type);
    }

    @Override
    public String toString() {
        return "Got it. I've added this task:\n" + task
                + "\nNow you have " + getTaskList().getTasks().size() + " tasks in the list.";
    }
}
