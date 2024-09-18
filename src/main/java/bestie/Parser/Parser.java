package bestie.Parser;

import java.time.format.DateTimeParseException;

import bestie.command.*;
import bestie.task.Event;
import bestie.task.Priority;


/**
 * Creates an instance of the parser to understand user input and executes command.
 */
public class Parser {
    /**
     * Parses user input to understand command and execute the corresponding command.
     *
     * @param userInput user input through CLI
     * @return command object corresponding to user's command input
     */
    public static Command parse(String userInput) {
        String[] parts = userInput.split(" ");
        String commandWord = parts[0];

        switch(commandWord) {
        case("bye"):
            return new ExitCommand();

        case("list"):
            return new ListCommand();

        case("mark"):
            try {
                return new MarkCommand(Integer.parseInt(parts[1]) - 1);
            } catch (ArrayIndexOutOfBoundsException e) {
                return new ErrorCommand("Please indicate the index of the task you want to mark as complete");
            }

        case("unmark"):
            try {
                return new UnmarkCommand(Integer.parseInt(parts[1]) - 1);
            } catch (ArrayIndexOutOfBoundsException e) {
                return new ErrorCommand("Please indicate the index of the task you want to unmark.");
            }

        case("find"):
            return new FindCommand(parts[1]);

        case("priority"):
            PriorityParser priorityParser = new PriorityParser();
            return priorityParser.executePriorityCommand(userInput);

        case("delete"):
            return new DeleteCommand(Integer.parseInt(parts[1]) - 1);

        case("todo"):
            TodoParser todoParser = new TodoParser();
            return todoParser.executeCommand(userInput);

        case("deadline"):
            DeadlineParser deadlineParser = new DeadlineParser();
            return deadlineParser.executeDeadlineCommand(userInput);

        case("event"):
            EventParser eventParser = new EventParser();
            return eventParser.executeEventCommand(userInput);

        default:
            return new InvalidCommand();
        }
    }
}
