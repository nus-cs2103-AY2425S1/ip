package orion.commands;

import orion.exceptions.OrionException;
import orion.exceptions.OrionInputException;
import orion.tasks.Todo;
import orion.utils.Parser;
import orion.utils.TaskList;

/**
 * Represents a command to add a todo task to the task list.
 * <p>
 * It extends {@link AddTaskCommand} and parses the user input to create a todo task.
 * This command checks for the correct syntax and constructs a Todo with the task
 * description, which is then added to the {@link TaskList} by {@link AddTaskCommand}.
 * </p>
 */
public class AddTodoCommand extends AddTaskCommand {

    /**
     * Constructs an AddTodoCommand that adds a {@link Todo} task.
     * Parses the input command to create a valid Todo object and passes it to the parent class
     * to be added to the {@link TaskList}.
     *
     * @param command The user input command split into a string array.
     * @throws OrionException If the command format is invalid or an error occurs during task creation.
     */
    public AddTodoCommand(String[] command) throws OrionInputException {
        super(parseToTask(command));
    }

    /**
     * Parses the command to create a {@link Todo} task. Ensures the correct syntax is used.
     *
     * @param command The array containing the user command parts.
     * @return A {@link Todo} task if the command is valid.
     * @throws OrionException If the command does not follow the expected syntax
     *                        ("todo &lt;task&gt;"), or if an error occurs during task creation.
     */
    private static Todo parseToTask(String[] command) throws OrionInputException {
        if (command.length < 2) {
            throw new OrionInputException("Correct syntax: todo <task>");
        } else {
            return new Todo(Parser.removeFirstWordFromString(String.join(" ", command)).trim());
        }
    }
}
