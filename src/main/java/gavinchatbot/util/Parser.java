package gavinchatbot.util;

import gavinchatbot.command.*;

import java.time.LocalDate;

/**
 * Represents a parser that interprets user input and returns the appropriate command.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input as a string.
     * @return The command corresponding to the user input.
     * @throws GavinException If the user input is invalid or cannot be parsed.
     */
    public Command parse(String input) throws GavinException {
        if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            return new MarkCommand(index);
        } else if (input.startsWith("unmark")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            return new UnmarkCommand(index);
        } else if (input.startsWith("todo")) {
            String description = input.substring(5).trim();
            return new AddToDoCommand(description);
        } else if (input.startsWith("deadline")) {
            String[] parts = input.substring(9).split("/by");
            String description = parts[0].trim();
            LocalDate by = LocalDate.parse(parts[1].trim());
            return new AddDeadlineCommand(description, by);
        } else if (input.startsWith("event")) {
            String[] parts = input.substring(6).split("/from");
            String description = parts[0].trim();
            String[] timeParts = parts[1].split("/to");
            String from = timeParts[0].trim();
            String to = timeParts[1].trim();
            return new AddEventCommand(description, from, to);
        } else if (input.startsWith("delete")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            return new DeleteCommand(index);
        } else if (input.startsWith("find")){
            String find = input.substring(5).trim();
            return new FindCommand(find);
        } else {
            throw new GavinException("Invalid input!!! \n" +
                    "To add a new task, please start with 'todo', 'deadline', or 'event'. \n" +
                    "For deadlines, enter the task, followed with '/by YYYY-MM-DD'. \n" +
                    "To view the list of tasks, please type 'list'. \n" +
                    "To mark/unmark the tasks, please type 'mark' or 'unmark' , followed by the index of the task. \n" +
                    "To delete an existing task, please type 'delete' , followed by the index of the task. \n" +
                    "To exit, please type 'bye'. ");
        }
    }
}
