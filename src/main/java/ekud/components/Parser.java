package ekud.components;

import ekud.commands.*;
import ekud.exceptions.EkudException;
import ekud.task.Task;

import java.util.HashMap;

public class Parser {
    public static int parseInt(String input) throws EkudException {
        try {
            return Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            String error = String.format("""
                            Now look what you've done!!
                            I thought it was obvious... But '%s' is clearly not an Integer!""",
                    input);
            throw new EkudException(error);
        }
    }

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

    public static Command parse(String command) throws EkudException {
        HashMap<String, String> tokenMap = tokenize(command);
        Command.Type type = Command.Type.getType(tokenMap.get("command"));
        String argument = tokenMap.get("argument");
        return switch (type) {
            case ADD -> new AddCommand(Task.getTaskFromTokens(tokenMap));
            case DELETE -> new DeleteCommand(parseInt(argument));
            case MARK -> new MarkCommand(parseInt(argument));
            case UNMARK -> new UnmarkCommand(parseInt(argument));
            case LIST -> new ListCommand();
            case EXIT -> new ExitCommand();
        };
    }
}
