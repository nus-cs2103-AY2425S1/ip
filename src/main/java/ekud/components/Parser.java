package ekud.components;

import java.util.HashMap;

import ekud.commands.AddCommand;
import ekud.commands.Command;
import ekud.commands.DeleteCommand;
import ekud.commands.ExitCommand;
import ekud.commands.FindCommand;
import ekud.commands.ListCommand;
import ekud.commands.MarkCommand;
import ekud.commands.UnmarkCommand;
import ekud.exceptions.EkudException;
import ekud.task.Task;

/**
 * The Parser of user inputs to {@link Command Commands}.
 */
public class Parser {
    /**
     * Parses an input {@link String} into an integer.
     * @param input The input.
     * @return The {@code int} representation of the input.
     * @throws EkudException If {@code input} cannot be read as an integer.
     */
    public static int parseInt(String input) throws EkudException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            String error = String.format("""
                            Now look what you've done!!
                            I thought it was obvious... But '%s' is clearly not an Integer!""",
                    input);
            throw new EkudException(error);
        }
    }

    /**
     * Converts a command {@link String} into a set of tokens.
     *
     * @param command The input command by the user.
     * @return A {@link HashMap} representing the set of tokens.
     */
    private static HashMap<String, String> tokenize(String command) {
        // splits command into a list of words separated by space
        String[] words = command.split("\\s");

        StringBuilder tokenBuilder = new StringBuilder();
        HashMap<String, String> tokenMap = new HashMap<>();
        // get command
        tokenMap.put("command", words[0]);
        // get params
        String currToken = "argument";
        for (int i = 1; i < words.length; i++) {
            // encounter optional token
            if (!words[i].isEmpty() && words[i].charAt(0) == '/') {
                tokenMap.put(currToken, tokenBuilder.toString());
                currToken = words[i];
                tokenBuilder.setLength(0); // reset builder
            } else {
                if (!tokenBuilder.isEmpty()) { // add space in between words
                    tokenBuilder.append(" ");
                }
                tokenBuilder.append(words[i]);
            }
        }
        tokenMap.put(currToken, tokenBuilder.toString());
        return tokenMap;
    }

    /**
     * Parses a user command into a {@link Command} instance.
     *
     * @param command The command {@link String}.
     * @return The {@link Command} corresponding to the {@code command}.
     * @throws EkudException If the {@code command} cannot be matched with any {@link Command}.
     */
    public static Command parse(String command) throws EkudException {
        HashMap<String, String> tokenMap = tokenize(command);
        Command.Type type = Command.Type.getType(tokenMap.get("command"));
        String argument = tokenMap.get("argument");
        // CHECKSTYLE.OFF: Indentation
        return switch (type) {
            case ADD -> new AddCommand(Task.getTaskFromTokens(tokenMap));
            case DELETE -> new DeleteCommand(parseInt(argument) - 1);
            case MARK -> new MarkCommand(parseInt(argument) - 1);
            case UNMARK -> new UnmarkCommand(parseInt(argument) - 1);
            case LIST -> new ListCommand();
            case FIND -> new FindCommand(argument);
            case EXIT -> new ExitCommand();
        };
        // CHECKSTYLE.ON: Indentation
    }
}
