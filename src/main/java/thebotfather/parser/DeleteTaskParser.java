package thebotfather.parser;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import thebotfather.command.Command;
import thebotfather.command.DeleteCommand;
import thebotfather.util.TheBotFatherException;

/**
 * The {@code DeleteTaskParser} class is responsible for parsing user input tokens
 * to create a {@link DeleteCommand} to remove a task by its index.
 */
public class DeleteTaskParser {

    /**
     * Parses the given {@link StringTokenizer} to extract the task index for deletion.
     *
     * @param tokens the {@code StringTokenizer} containing the input to be parsed
     * @return a {@code DeleteCommand} to delete the task at the given index
     * @throws TheBotFatherException if no index is provided or if more than one token is found
     */
    public static Command parse(StringTokenizer tokens) throws TheBotFatherException {
        try {
            String index = tokens.nextToken();
            if (tokens.hasMoreTokens()) {
                throw new TheBotFatherException("Just one number please :/\n"
                        + "To delete a task as done enter \"delete <index>\"");
            }
            return new DeleteCommand(String.valueOf(index));
        } catch (NoSuchElementException e) {
            throw new TheBotFatherException("Skill issue: Atleast enter a number.\n"
                    + "To delete a task enter \"delete <index>\"");
        }
    }
}
