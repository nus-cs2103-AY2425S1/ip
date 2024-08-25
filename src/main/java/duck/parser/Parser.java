package duck.parser;

import duck.commands.*;



public class Parser {
    public static Command parse(String message) {
        try {

            CommandWord instruction = CommandWord.valueOf(getInstruction(message));
            return switch (instruction) {
            case LIST -> new ListCommand(message);
            case FIND -> new FindCommand(message);
            case MARK -> new MarkCommand(message);
            case UNMARK -> new UnmarkCommand(message);
            case DELETE -> new DeleteCommand(message);
            case TODO -> new ToDoCommand(message);
            case DEADLINE -> new DeadlineCommand(message);
            case EVENT -> new EventCommand(message);
            case ON -> new OnCommand(message);
            case BYE -> new ByeCommand(message);

            };

        } catch (IllegalArgumentException e) {
            return new InvalidCommand("");
        }
    }

    private static String getInstruction(String message) {
        return message.split(" ")[0].toUpperCase();
    }
}
