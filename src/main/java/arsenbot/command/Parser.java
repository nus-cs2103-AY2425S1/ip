package arsenbot.command;

import arsenbot.task.TaskManagerException;

/**
 * The Parser class is responsible for interpreting user input and returning the corresponding command.
 * It maps user input to specific Command objects based on the input string.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     * Recognizes commands for tasks (ToDo, Deadline, Event), listing tasks, deleting tasks,
     * marking tasks, unmarking tasks, exiting the application, and finding tasks by keyword.
     *
     * @param input the user input string
     * @return the corresponding Command object based on the user input
     * @throws TaskManagerException if the input does not match any recognized command
     */
    public static Command parse(String input) throws TaskManagerException {

        String[] parts = input.split(" ", 2); // Split input into command and arguments

        return switch (parts[0]) {
            case "todo", "deadline", "event" -> new AddCommand(input);
            case "list" -> new ListCommand();
            case "delete" -> new DeleteCommand(input);
            case "mark" -> new MarkCommand(input);
            case "unmark" -> new UnmarkCommand(input);
            case "bye" -> new ExitCommand();
            case "find" -> new FindCommand(input);
            case "help" -> new HelpCommand();
            default ->
                    throw new TaskManagerException("Error: Unrecognized command. Please enter a valid task command.");
        };

    }
}
