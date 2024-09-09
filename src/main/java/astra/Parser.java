package astra;

import java.util.HashMap;

import astra.command.AddCommand;
import astra.command.Command;
import astra.command.DeleteCommand;
import astra.command.ExitCommand;
import astra.command.FindCommand;
import astra.command.ListCommand;
import astra.command.MarkCommand;
import astra.task.Task;

/**
 * Parser class to parse user input into commands.
 */
public class Parser {

    /**
     * Returns the index argument of a command.
     *
     * @param fullCommand full command with its arguments.
     * @return Index specified in the command.
     * @throws AstraException If index is invalid.
     */
    static int getIndex(String fullCommand) throws AstraException {
        int index;
        try {
            index = Integer.parseInt(fullCommand.split(" ")[1]);
        } catch (Exception e) {
            throw new AstraException("Invalid index.");
        }
        return index;
    }

    /**
     * Returns the key word arguments of a command.
     *
     * @param fullCommand Command text with its arguments.
     * @return Map of each key word to its string value.
     */
    static HashMap<String, String> getArgs(String fullCommand) {
        String[] words = fullCommand.split(" ");
        int len = words.length;
        HashMap<String, String> args = new HashMap<>();
        String key = "main";
        for (int i = 1; i < len; i++) {
            String word = words[i];
            boolean isNotEmpty = !word.isEmpty();
            boolean isKey = isNotEmpty && word.charAt(0) == '/';
            if (isKey) {
                key = word.substring(1);
            } else {
                // Append word to existing key or create new key
                if (args.containsKey(key)) {
                    args.put(key, args.get(key) + " " + word);
                } else {
                    args.put(key, word);
                }
            }
        }
        return args;
    }

    /**
     * Parses the full command into a Command object.
     *
     * @param fullCommand Full command text.
     * @return Command object.
     * @throws AstraException If command is invalid.
     */
    public static Command parse(String fullCommand) throws AstraException {
        String commandWord = fullCommand.split(" ")[0];
        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "delete":
            return new DeleteCommand(getIndex(fullCommand));
        case "mark":
            return new MarkCommand(getIndex(fullCommand), true);
        case "unmark":
            return new MarkCommand(getIndex(fullCommand), false);
        case "todo":
            return new AddCommand(Task.TaskType.TODO, getArgs(fullCommand));
        case "deadline":
            return new AddCommand(Task.TaskType.DEADLINE, getArgs(fullCommand));
        case "event":
            return new AddCommand(Task.TaskType.EVENT, getArgs(fullCommand));
        case "find":
            return new FindCommand(getArgs(fullCommand).getOrDefault("main", ""));
        default:
            throw new AstraException("Unknown command.");
        }
    }
}
