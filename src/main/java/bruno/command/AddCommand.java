package bruno.command;

import bruno.Bruno;
import bruno.task.TaskList;
import bruno.Ui;
import bruno.exceptions.BrunoException;

/**
 * Represents a command to add a new task to the task list.
 * This command encapsulates the information needed to add a task of a specified type
 * to the task list and handles the execution of the task addition.
 */
public class AddCommand extends Command {
    private String description;
    private Bruno.TaskType type;

    /**
     * Constructs an AddCommand with the specified task list, description, and task type.
     *
     * @param tasks        The task list to which the new task will be added.
     * @param description  The description of the task to be added.
     * @param type         The type of the task to be added.
     */
    public AddCommand(TaskList tasks, String description, Bruno.TaskType type) {
        super(tasks);
        this.description = description;
        this.type = type;
    }

    /**
     * Executes the command by adding the new task with the specified description and type
     * to the task list. If an error occurs during task addition, an error message is printed.
     */
    @Override
    public void execute() {
        try {
            getTasks().addTask(description, type);
        } catch (BrunoException e) {
            Ui.printErrorMessage(e);
        }
    }
}