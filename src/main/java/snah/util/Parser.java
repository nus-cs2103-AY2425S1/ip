package snah.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import snah.commands.Clear;
import snah.commands.Command;
import snah.commands.CreateDeadline;
import snah.commands.CreateEvent;
import snah.commands.CreateTodo;
import snah.commands.Delete;
import snah.commands.Find;
import snah.commands.Invalid;
import snah.commands.List;
import snah.commands.Mark;
import snah.commands.UnMark;
import snah.errors.ParsingException;

/**
 * Class to handle the parsing of the chatbot
 */
public class Parser {

    private static final Map<String, Function<String, Command>> commandMap = new HashMap<>();

    static {
        commandMap.put("LIST", List::new);
        commandMap.put("CLEAR", Clear::new);
        commandMap.put("MARK", Mark::new);
        commandMap.put("UNMARK", UnMark::new);
        commandMap.put("TODO", CreateTodo::new);
        commandMap.put("DEADLINE", CreateDeadline::new);
        commandMap.put("EVENT", CreateEvent::new);
        commandMap.put("DELETE", Delete::new);
        commandMap.put("FIND", Find::new);
    }

    /**
     * Returns the command from the user input
     * @param input User input
     * @return an executable command from the user input
     */
    public static Command getCommand(String input) {
        String commandSymbol = input.split(" ", 2)[0].toUpperCase();

        Function<String, Command> commandConstructor = commandMap.get(commandSymbol);

        if (commandConstructor != null) {
            return commandConstructor.apply(input);
        } else {
            return new Invalid();
        }
    }

    /**
     * Returns the raw command from the user input
     * @param input User input
     * @return Raw command from the user input
     */
    public static String getRawCommand(String input) {
        return input.split(" ", 2)[0];
    }

    /**
     * Returns the payload from the user input
     * @param input User input
     * @return Payload from the user input
     */
    public static String[] getTodoPayload(String input) throws ParsingException {
        String[] todoPayload = input.split(" ", 2);
        if (todoPayload.length == 1) {
            throw new ParsingException("The description of a todo cannot be empty.");
        }
        String todoDescription = input.split(" ", 2)[1];
        if (todoDescription.length() == 0) {
            throw new ParsingException("The description of a todo cannot be empty.");
        }
        return new String[] { todoDescription };
    }

    /**
     * Returns the deadline payload from the user input
     * @param input User input
     * @return Deadline payload from the user input
     */
    public static String[] getDeadlinePayload(String input) throws ParsingException {
        String[] deadlinePayload = input.split(" ", 2);
        if (deadlinePayload.length == 1) {
            throw new ParsingException("The description of a deadline cannot be empty.");
        }
        String[] splitInput = deadlinePayload[1].split(" /by ");
        if (splitInput.length != 2) {
            throw new ParsingException(
                    "The deadline command should be in the format: deadline <description> /by <date>");
        }
        return new String[] { splitInput[0], splitInput[1] };
    }

    /**
     * Returns the event payload from the user input
     * @param input User input
     * @return Event payload from the user input
     */
    public static String[] getEventPayload(String input) throws ParsingException {
        String[] eventPayload = input.split(" ", 2);
        if (eventPayload.length == 1) {
            throw new ParsingException("The description of an event cannot be empty.");
        }
        String[] splitInput = eventPayload[1].split(" /from ");
        if (splitInput.length != 2) {
            throw new ParsingException("The event command should be in the format: event <description> /from <date>");
        }
        String[] finalSplit = splitInput[1].split(" /to ");
        if (finalSplit.length != 2) {
            throw new ParsingException(
                    "The event command should be in the format: event <description> /from <date> /to <date>");
        }
        return new String[] { splitInput[0], finalSplit[0], finalSplit[1] };
    }

    /**
     * Returns the task index from the user input
     * @param input User input
     * @return Task index from the user input
     */
    public static int getTaskIndex(String input) throws ParsingException {
        String[] taskIndexPayload = input.split(" ", 2);
        if (taskIndexPayload.length == 1) {
            throw new ParsingException("The task index cannot be empty.");
        }
        try {
            return Integer.parseInt(input.split(" ", 2)[1].strip()) - 1;
        } catch (NumberFormatException e) {
            throw new ParsingException("The task index should be a number.");
        }
    }

    public static String getSearchQuery(String input) throws ParsingException {
        String[] searchPayload = input.split(" ", 2);
        if (searchPayload.length == 1) {
            throw new ParsingException("The search query cannot be empty.");
        }
        return input.split(" ", 2)[1];
    }

    public static String getCommandStrings() {
        String[] commandList = commandMap.keySet().toArray(new String[0]);
        String response = "";
        for (String command : commandList) {
            response += command + "\n";
        }
        return response;
    }

}
