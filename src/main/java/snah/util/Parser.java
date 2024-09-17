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
import snah.commands.Help;
import snah.commands.Invalid;
import snah.commands.List;
import snah.commands.Mark;
import snah.commands.UnMark;
import snah.errors.ParsingException;
import snah.task.Deadline;
import snah.task.Event;
import snah.task.ToDo;

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
        commandMap.put("HELP", Help::new);
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
            throw new ParsingException("The todo command should be in the format:\n" + ToDo.getFormatDescription());
        }
        String todoDescription = input.split(" ", 2)[1];
        todoDescription = todoDescription.strip();
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
                    "The deadline command should be in the format:\n" + Deadline.getFormatDescription());
        }
        if (splitInput[0].strip().length() == 0) {
            throw new ParsingException("The description of a deadline cannot be empty.");
        }
        if (splitInput[1].strip().length() == 0) {
            throw new ParsingException("The deadline of a deadline cannot be empty.");
        }
        splitInput[0] = splitInput[0].strip();
        splitInput[1] = splitInput[1].strip();

        try {
            String[] date = splitInput[1].split("-");
            if (date.length != 3) {
                throw new ParsingException("The deadline should be in the format yyyy-mm-dd.");
            }
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);
            if (year < 0 || month < 1 || month > 12 || day < 1 || day > 31) {
                throw new ParsingException("The deadline date should be in the format yyyy-mm-dd.");
            }
        } catch (NumberFormatException e) {
            throw new ParsingException("The deadline date should be in the format yyyy-mm-dd.");
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
            throw new ParsingException("The event command should be in the format:\n" + Event.getFormatDescription());
        }
        String[] finalSplit = splitInput[1].split(" /to ");
        if (finalSplit.length != 2) {
            throw new ParsingException("The event command should be in the format:\n" + Event.getFormatDescription());
        }
        if (splitInput[0].strip().length() == 0) {
            throw new ParsingException("The description of an event cannot be empty.");
        }
        if (finalSplit[0].strip().length() == 0) {
            throw new ParsingException("The start time of an event cannot be empty.");
        }
        if (finalSplit[1].strip().length() == 0) {
            throw new ParsingException("The end time of an event cannot be empty.");
        }
        splitInput[0] = splitInput[0].strip();
        finalSplit[0] = finalSplit[0].strip();
        finalSplit[1] = finalSplit[1].strip();
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
        return commandMap.keySet().stream().reduce("", (acc, command) -> acc + command + "\n");
    }

    public static String[] getCommandList() {
        return commandMap.keySet().toArray(new String[0]);
    }

    public static boolean isExit(String input) {
        return input.startsWith("bye");
    }

}
