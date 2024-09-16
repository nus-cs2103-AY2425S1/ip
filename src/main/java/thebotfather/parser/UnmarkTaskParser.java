package thebotfather.parser;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import thebotfather.command.Command;
import thebotfather.command.MarkCommand;
import thebotfather.util.TheBotFatherException;

/**
 * The {@code UnmarkTaskParser} class is responsible for parsing user input tokens
 * to create a {@link MarkCommand} that unmarks a task as not done based on its index.
 */
public class UnmarkTaskParser {

    /**
     * Parses the given {@link StringTokenizer} to extract the task index to be unmarked.
     *
     * @param tokens the {@code StringTokenizer} containing the task index to be unmarked
     * @return a {@code MarkCommand} that unmarks the task at the given index
     * @throws TheBotFatherException if no index is provided or if more than one token is found
     */
    public static Command parse(StringTokenizer tokens) throws TheBotFatherException {
        try {
            String index = tokens.nextToken();
            if (tokens.hasMoreTokens()) {
                throw new TheBotFatherException("Just one number please :/\n"
                        + "To unmark a task enter \"mark <index>\"");
            }
            return new MarkCommand(String.valueOf(index), false);
        } catch (NoSuchElementException e) {
            throw new TheBotFatherException("Skill issue: Atleast enter a number.\n"
                    + "To unmark a task enter \"unmark <index>\"");
        }
    }
}
