package orion.commands;

import java.util.Arrays;

import orion.exceptions.OrionException;
import orion.exceptions.OrionInputException;
import orion.tasks.Event;
import orion.utils.Parser;
import orion.utils.TaskList;

/**
 * Represents a command to add an event task to the task list.
 * <p>
 * It extends {@link AddTaskCommand} and parses the user input to create an event task.
 * This command checks for the correct syntax and constructs a Event with the task
 * description and start and end dates, which is then added to the {@link TaskList}
 * by {@link AddTaskCommand}.
 * </p>
 */
public class AddEventCommand extends AddTaskCommand {

    /**
     * Constructs an AddEventCommand that adds a {@link Event} task.
     * Parses the input command to create a valid Event object and passes it to the parent class
     * to be added to the {@link TaskList}.
     *
     * @param command The user input command split into a string array.
     * @throws OrionException If the command format is invalid or an error occurs during task creation.
     */
    public AddEventCommand(String[] command) throws OrionException {
        super(parseToTask(command));
    }

    /**
     * Parses the command to create a {@link Event} task. Ensures the correct syntax is used,
     * with the task description and start and end dates specified after the "/from" and "/to" keywords respectively.
     *
     * @param command The array containing the user command parts.
     * @return A {@link Event} task if the command is valid.
     * @throws OrionException If the command does not follow the expected syntax
     *                        ("event &lt;task&gt; /from &lt;yyyy-mm-dd&gt; /to &lt;yyyy-mm-dd&gt;"),
     *                        or if an error occurs during task creation.
     */
    private static Event parseToTask(String[] command) throws OrionException {
        if (command.length != 3 || !command[1].matches("^from.*$")
                || !command[2].matches("^to.*$")) {
            throw new OrionInputException("Correct syntax: event <task> /from <yyyy-mm-dd> /to <yyyy-mm-dd>");
        } else {
            String[] mapped = Arrays.stream(command)
                    .map(Parser::removeFirstWordFromString)
                    .toArray(String[]::new);
            return new Event(mapped[0].trim(), mapped[1].trim(), mapped[2].trim());
        }
    }
}
