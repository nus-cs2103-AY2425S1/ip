package thebotfather.parser;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import thebotfather.command.Command;
import thebotfather.command.MarkCommand;
import thebotfather.util.TheBotFatherException;

/**
 * The {@code MarkTaskParser} class is responsible for parsing user input tokens
 * to create a {@link MarkCommand} that marks a task as done based on its index.
 */
public class MarkTaskParser {

    /**
     * Parses the given {@link StringTokenizer} to extract the task index to be marked as done.
     *
     * @param tokens the {@code StringTokenizer} containing the task index to be marked as done
     * @return a {@code MarkCommand} that marks the task at the given index as completed
     * @throws TheBotFatherException if no index is provided or if more than one token is found
     */
    public static Command parse(StringTokenizer tokens) throws TheBotFatherException {
        try {
            String index = tokens.nextToken();
            if (tokens.hasMoreTokens()) {
                throw new TheBotFatherException("Just one number please :/\n"
                        + "To mark a task as done enter \"mark <index>\"");
            }
            return new MarkCommand(String.valueOf(index), true);
        } catch (NoSuchElementException e) {
            throw new TheBotFatherException("Skill issue: Atleast enter a number.\n"
                    + "To mark a task as done enter \"mark <index>\"");
        }
    }
}
