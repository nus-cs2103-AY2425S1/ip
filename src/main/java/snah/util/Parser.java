package snah.util;

/**
 * Class to handle the parsing of the chatbot
 */
public class Parser {

    /**
     * Enum to represent the commands that the chatbot can handle
     */
    public enum Command {
        BYE, LIST, MARK, UNMARK, DEADLINE, EVENT, TODO, DELETE, CLEAR, INVALID, FIND
    }

    /**
     * Returns the command from the user input
     * @param input User input
     * @return Command from the user input
     */
    public static Command getCommand(String input) {
        String commandSymbol = input.split(" ", 2)[0].toUpperCase();
        switch (commandSymbol) {
        case "LIST":
            return Command.LIST;
        case "CLEAR":
            return Command.CLEAR;
        case "MARK":
            return Command.MARK;
        case "UNMARK":
            return Command.UNMARK;
        case "DEADLINE":
            return Command.DEADLINE;
        case "EVENT":
            return Command.EVENT;
        case "TODO":
            return Command.TODO;
        case "DELETE":
            return Command.DELETE;
        case "BYE":
            return Command.BYE;
        case "FIND":
            return Command.FIND;
        default:
            return Command.INVALID;
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
    public static String[] getTodoPayload(String input) {
        String[] todoPayload = input.split(" ", 2);
        if (todoPayload.length == 1) {
            return null;
        }
        String todoDescription = input.split(" ", 2)[1];
        if (todoDescription.length() == 0) {
            return null;
        }
        return new String[] { todoDescription };
    }

    /**
     * Returns the deadline payload from the user input
     * @param input User input
     * @return Deadline payload from the user input
     */
    public static String[] getDeadlinePayload(String input) {
        String[] deadlinePayload = input.split(" ", 2);
        if (deadlinePayload.length == 1) {
            return null;
        }
        String[] splitInput = deadlinePayload[1].split(" /by ");
        if (splitInput.length != 2) {
            return null;
        }
        return new String[] { splitInput[0], splitInput[1] };
    }

    /**
     * Returns the event payload from the user input
     * @param input User input
     * @return Event payload from the user input
     */
    public static String[] getEventPayload(String input) {
        String[] eventPayload = input.split(" ", 2);
        if (eventPayload.length == 1) {
            return null;
        }
        String[] splitInput = eventPayload[1].split(" /from ");
        if (splitInput.length != 2) {
            return null;
        }
        String[] finalSplit = splitInput[1].split(" /to ");
        if (finalSplit.length != 2) {
            return null;
        }
        return new String[] { splitInput[0], finalSplit[0], finalSplit[1] };
    }

    /**
     * Returns the task index from the user input
     * @param input User input
     * @return Task index from the user input
     */
    public static int getTaskIndex(String input) {
        return Integer.parseInt(input.split(" ", 2)[1].strip()) - 1;
    }

    public static String getSearchQuery(String input) {
        String[] searchPayload = input.split(" ", 2);
        if (searchPayload.length == 1) {
            return null;
        }
        return input.split(" ", 2)[1];
    }

    public static String[] getCommandStrings() {
        return Command.values().toString().replaceAll("^.|.$", "").split(", ");
    }

}
