package command;

import exception.KukiShinobuException;
import storage.Storage;
import task.TaskList;
import task.Todo;
import ui.Ui;

/**
 * Represents a command to add a <code>Todo</code> <code>Task</code> to the <code>TaskList</code>.
 * It takes in a task description as an argument and adds it to the list of tasks.
 */
public class AddTodoCommand extends Command {
    private final Todo todo;

    /**
     * Creates an AddTodoCommand with the specified arguments.
     * If the argument is empty, it throws a KukiShinobuException indicating the description is missing.
     *
     * @param arguments The string containing the task description.
     * @throws KukiShinobuException if the task description is missing.
     */
    public AddTodoCommand(String arguments) throws KukiShinobuException {
        if (arguments.isEmpty()) {
            throw new KukiShinobuException("Todo is missing description!");
        }
        // argument is taskDescription
        this.todo = new Todo(arguments);
    }

    /**
     * Executes the command by adding the todo task to the task list.
     *
     * @param taskList The TaskList where the todo task will be added.
     * @param ui       The Ui instance for user interaction.
     * @param storage  The Storage instance to save the updated task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.addTask(this.todo);
    }
}
