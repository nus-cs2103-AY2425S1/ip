package orion.commands;

import java.util.Arrays;

import orion.exceptions.OrionException;
import orion.exceptions.OrionInputException;
import orion.tasks.Deadline;
import orion.utils.Parser;
import orion.utils.TaskList;

/**
 * Represents a command to add a deadline task to the task list.
 * <p>
 * It extends {@link AddTaskCommand} and parses the user input to create a deadline task.
 * This command checks for the correct syntax and constructs a Deadline with the task
 * description and due date, which is then added to the {@link TaskList} by {@link AddTaskCommand}.
 * </p>
 */
public class AddDeadlineCommand extends AddTaskCommand {

    /**
     * Constructs an AddDeadlineCommand that adds a {@link Deadline} task.
     * Parses the input command to create a valid Deadline object and passes it to the parent class
     * to be added to the {@link TaskList}.
     *
     * @param command The user input command split into a string array.
     * @throws OrionException If the command format is invalid or an error occurs during task creation.
     */
    public AddDeadlineCommand(String[] command) throws OrionException {
        super(parseToTask(command));
    }

    /**
     * Parses the command to create a {@link Deadline} task. Ensures the correct syntax is used,
     * with the task description and deadline specified after the "/by" keyword.
     *
     * @param command The array containing the user command parts.
     * @return A {@link Deadline} task if the command is valid.
     * @throws OrionException If the command does not follow the expected syntax
     *                        ("deadline &lt;task&gt; /by &lt;yyyy-mm-dd&gt;"), or if an error
     *                        occurs during task creation.
     */
    private static Deadline parseToTask(String[] command) throws OrionException {
        if (command.length != 2 || !command[1].matches("^by.*$")) {
            throw new OrionInputException("Correct syntax: deadline <task> /by <yyyy-mm-dd>");
        } else {
            String[] mapped = Arrays.stream(command)
                    .map(Parser::removeFirstWordFromString)
                    .toArray(String[]::new);
            return new Deadline(mapped[0].trim(), mapped[1].trim());
        }
    }
}
