package drbrown.utils;

import drbrown.command.Command;
import drbrown.parsing.ByeParser;
import drbrown.parsing.DeadlineParser;
import drbrown.parsing.DeleteParser;
import drbrown.parsing.EventParser;
import drbrown.parsing.FindParser;
import drbrown.parsing.ListParser;
import drbrown.parsing.MarkParser;
import drbrown.parsing.TodoParser;
import drbrown.parsing.UnmarkParser;

/**
 * A general parser that interprets user input and directs it to the appropriate
 * command-specific parser to create the corresponding {@link Command}.
 * The parser identifies the command type from the user's input and processes it accordingly.
 */
public class Parser {

    /**
     * Parses the user input to determine which command to execute.
     *
     * @param userInput The full input string entered by the user.
     * @return An instance of {@link Command} that represents the user request.
     * @throws DrBrownException If the input command is unrecognized or if specific parsers throw exceptions.
     */
    public static Command parse(String userInput) throws DrBrownException {
        String[] inputSplit = userInput.split(" ", 2);
        return switch (inputSplit[0]) {
        case "todo" -> TodoParser.parse(inputSplit);
        case "deadline" -> DeadlineParser.parse(inputSplit);
        case "event" -> EventParser.parse(userInput, inputSplit);
        case "mark" -> MarkParser.parse(inputSplit);
        case "unmark" -> UnmarkParser.parse(inputSplit);
        case "list" -> ListParser.parse(inputSplit);
        case "delete" -> DeleteParser.parse(inputSplit);
        case "bye" -> ByeParser.parse(inputSplit);
        case "find" -> FindParser.parse(inputSplit);
        default -> throw new DrBrownException("I'm from the future, and even I don't know what that means.");
        };
    }
}
