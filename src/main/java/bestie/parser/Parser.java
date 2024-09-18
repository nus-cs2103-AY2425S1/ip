package bestie.parser;

import bestie.command.*;

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
            MarkParser markParser = new MarkParser();
            return markParser.executeMarkCommand(userInput);

        case("unmark"):
            UnmarkParser unmarkParser = new UnmarkParser();
            return unmarkParser.executeUnmarkCommand(userInput);

        case("find"):
            FindParser findParser = new FindParser();
            return findParser.executeFindCommand(userInput);

        case("priority"):
            PriorityParser priorityParser = new PriorityParser();
            return priorityParser.executePriorityCommand(userInput);

        case("delete"):
            DeleteParser deleteParser = new DeleteParser();
            return deleteParser.executeDeleteCommand(userInput);

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
