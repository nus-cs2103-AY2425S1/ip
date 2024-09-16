package thebotfather.parser;

import java.util.StringTokenizer;

import thebotfather.command.Command;
import thebotfather.command.FindCommand;
import thebotfather.util.TheBotFatherException;

/**
 * The {@code FindCommandParser} class is responsible for parsing user input tokens
 * to create a {@link FindCommand} to search for tasks by a keyword or phrase.
 */
public class FindCommandParser {

    /**
     * Parses the given {@link StringTokenizer} to create a {@link FindCommand}.
     *
     * @param tokens the {@code StringTokenizer} containing the keyword or phrase to find
     * @return a {@code FindCommand} that searches for tasks matching the input
     * @throws TheBotFatherException if the input is invalid or cannot be processed
     */
    public static Command parse(StringTokenizer tokens) throws TheBotFatherException {
        return new FindCommand(tokens);
    }
}
