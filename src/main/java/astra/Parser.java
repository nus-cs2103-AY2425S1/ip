package astra;

import astra.command.Command;
import astra.command.AddCommand;
import astra.command.DeleteCommand;
import astra.command.ExitCommand;
import astra.command.ListCommand;
import astra.command.MarkCommand;
import astra.task.Task;

import java.util.HashMap;

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
            if (word.charAt(0) == '/') {
                key = word.substring(1);
            } else {
                if (args.containsKey(key)) {
                    args.put(key, args.get(key) + " " + word);
                } else {
                    args.put(key, word);
                }
            }
        }
        return args;
    }


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
        default:
            throw new AstraException("Unknown command.");
        }
    }
}
