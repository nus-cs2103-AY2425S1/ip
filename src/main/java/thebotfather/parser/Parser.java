package thebotfather.parser;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import thebotfather.command.Command;
import thebotfather.command.CommandList;
import thebotfather.util.TheBotFatherException;
import thebotfather.util.Ui;

/**
 * The {@code Parser} class is responsible for interpreting user input
 * and converting it into a {@link Command}. It parses the string input
 * to determine which command the user wants to execute and returns the corresponding
 * {@link Command} object.
 */
public class Parser {

    /**
     * Parses the user's input and returns the corresponding {@link Command} object.
     *
     * @param completeLine The full line of input entered by the user.
     * @param ui           The {@code Ui} object used to interact with the user interface.
     * @return The {@code Command} object corresponding to the user's input.
     * @throws TheBotFatherException If the input is invalid or does not correspond to any known command.
     */
    public static Command parse(String completeLine, Ui ui) throws TheBotFatherException {

        StringTokenizer tokens;
        String command;

        try {
            tokens = new StringTokenizer(completeLine);
            command = tokens.nextToken();
        } catch (NoSuchElementException e) {
            throw new TheBotFatherException("OOPS!!! I'm sorry, but I don't know what that means :-(.\n"
                    + "Use \"bye\" if you want to exit the program");
        }

        return switch (CommandList.findCommand(command)) {
        case BYE -> ExitParser.parse();
        case LIST -> TaskListParser.parse();
        case MARK -> MarkTaskParser.parse(tokens);
        case UNMARK -> UnmarkTaskParser.parse(tokens);
        case DELETE -> DeleteTaskParser.parse(tokens);
        case FIND -> FindCommandParser.parse(tokens);
        case TODO -> TodoParser.parse(tokens, ui);
        case EVENT -> EventParser.parse(tokens, ui);
        case DEADLINE -> DeadlineParser.parse(tokens, ui);
        default -> throw new TheBotFatherException("OOPS!!! I'm sorry, but I don't know what that means :-(.\n"
                + "Use \"bye\" if you want to exit the program");
        };
    }
}
