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
        case "todo" -> new TodoParser(inputSplit).parse();
        case "deadline" -> new DeadlineParser(inputSplit).parse();
        case "event" -> new EventParser(userInput, inputSplit).parse();
        case "mark" -> new MarkParser(inputSplit).parse();
        case "unmark" -> new UnmarkParser(inputSplit).parse();
        case "list" -> new ListParser(inputSplit).parse();
        case "delete" -> new DeleteParser(inputSplit).parse();
        case "bye" -> new ByeParser(inputSplit).parse();
        case "find" -> new FindParser(inputSplit).parse();
        default -> throw new DrBrownException(Ui.getDefaultException());
        };

    }
}
