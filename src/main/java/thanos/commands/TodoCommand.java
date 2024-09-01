package thanos.commands;

import static thanos.utility.ResponseFormatter.generateTaskAddedResponse;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.TaskList;
import thanos.tasks.Todo;

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
     * This method processes the task description from the command argument, creates a new {@code Todo} task with the
     * description, and adds it to the {@code TaskList}. It then returns a confirmation message indicating that the task
     * has been added. If no task description is provided in the argument, an {@code InvalidCommandException} is thrown.
     * </p>
     *
     * @param taskList The {@code TaskList} to which the new TODO task will be added.
     * @return A formatted string confirming that the task has been added to the list.
     * @throws InvalidCommandException If no task description is provided in the command argument.
     */
    @Override
    public String execute(TaskList taskList) throws InvalidCommandException {
        if (this.getArgument().isEmpty()) {
            throw new InvalidCommandException(
                    "No task description provided. Please use the correct format: 'todo [task]'"
            );
        }

        Todo todo = new Todo(this.getArgument());
        taskList.add(todo);
        return generateTaskAddedResponse(todo, taskList.size());
    }
}
