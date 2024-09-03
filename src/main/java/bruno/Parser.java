package bruno;

import bruno.command.*;
import bruno.exceptions.BrunoException;
import bruno.exceptions.UnknownCommandException;
import bruno.task.TaskList;

/**
 * The Parser class is responsible for interpreting user input and
 * converting it into commands for execution.
 * It takes in raw input from the user, identifies the type of command,
 * and generates a Command object based on that input.
 */
public class Parser {
    /**
     * Parses the given user command string and returns the corresponding Command object.
     *
     * @param command The raw input string from the user.
     * @param tasks The TaskList containing the user's tasks, used to apply the command.
     * @return A Command object corresponding to the parsed command, or null if an exception occurs.
     */
    public static Command parse(String command, TaskList tasks) throws BrunoException {
        command = command.trim();

        String[] parts = command.split(" ", 2);
        String firstWord = parts[0];
        String restOfString = parts.length > 1 ? parts[1] : "";

        if (command.equalsIgnoreCase("bye")) {
            return new ExitCommand(tasks);
        } else if (command.equalsIgnoreCase("list")) {
            return new ListCommand(tasks);
        } else if (firstWord.equalsIgnoreCase("mark")) {
            String[] taskNums = restOfString.split(" ");
            return new MarkCommand(tasks, taskNums);
        } else if (firstWord.equalsIgnoreCase("unmark")) {
            String[] taskNums = restOfString.split(" ");
            return new UnmarkCommand(tasks, taskNums);
        } else if (firstWord.equalsIgnoreCase("delete")) {
            String[] taskNums = restOfString.split(" ");
            return new DeleteCommand(tasks, taskNums);
        } else if (firstWord.equalsIgnoreCase("todo")) {
            return new AddCommand(tasks, restOfString, Bruno.TaskType.TODO);
        } else if (firstWord.equalsIgnoreCase("deadline")) {
            return new AddCommand(tasks, restOfString, Bruno.TaskType.DEADLINE);
        } else if (firstWord.equalsIgnoreCase("event")) {
            return new AddCommand(tasks, restOfString, Bruno.TaskType.EVENT);
        } else if (firstWord.equalsIgnoreCase("find")) {
            return new FindCommand(tasks, restOfString);
        } else {
            return new UnknownCommand(tasks);
        }
    }
}
