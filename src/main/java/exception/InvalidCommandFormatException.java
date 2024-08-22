package exception;

import java.util.Map;

public class InvalidCommandFormatException extends BotException {
    private static final Map<String, String> COMMAND_FORMAT_MAP = Map.of(
            "todo", "todo <description>",
            "deadline", "deadline <description> /by <end date>",
            "event", "event <description> /from <start date> /to <end date>",
            "list", "list",
            "mark", "mark <task index>",
            "unmark", "unmark <task index>"
    );
    public InvalidCommandFormatException(String commandType) {
        super(InvalidCommandFormatException.generateMessage(commandType));
    }

    private static String generateMessage(String commandType) {
        String message = "Invalid command format for " + commandType + "!\n";
        message = message + "Correct usage: " + COMMAND_FORMAT_MAP.get(commandType);
        return message;
    }
}
