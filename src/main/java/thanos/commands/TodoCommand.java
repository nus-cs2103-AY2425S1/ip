package thanos.commands;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.TaskList;
import thanos.tasks.Todo;
import thanos.ui.Ui;

/**
 * Represents a command to add a todo task to the {@code TaskList}.
 */
public class TodoCommand extends Command {
    /**
     * Constructs a {@code TodoCommand} with the given argument.
     *
     * @param argument the argument associated with this command. The argument should be a string in the format:
     *                 'todo [task description]'.
     */
    public TodoCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the command to add a new TODO task to the {@code TaskList}.
     * <p>
     * This method processes the task description from the argument, creates a new {@code Todo} task with the
     * description, adds it to the {@code TaskList}, and displays a message indicating that the task has been added.
     * If the argument is empty, an {@code InvalidCommandException} is thrown.
     * </p>
     *
     * @param taskList the list of tasks to which the new TODO task will be added.
     * @param ui the user interface component used to display the task addition confirmation to the user.
     *
     * @throws InvalidCommandException if no task description is provided.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidCommandException {
        if (this.getArgument().isEmpty()) {
            throw new InvalidCommandException(
                    "No task description provided. Please use the correct format: 'todo [task]'"
            );
        }

        Todo todo = new Todo(this.getArgument());
        taskList.add(todo);
        ui.displayTaskAdded(todo, taskList.size());
    }
}
