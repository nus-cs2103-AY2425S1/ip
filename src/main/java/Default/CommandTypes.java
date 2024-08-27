package Default;

import Commands.*;

import java.util.regex.Pattern;
public enum CommandTypes {
    MARK(new MarkCommand()),
    UNMARK(new UnmarkCommand()),
    LIST(new ListCommand()),
    DELETE(new DeleteCommand()),
    TODO(new AddToDoCommand()),
    DEADLINE(new AddDeadlineCommand()),
    EVENT(new AddEventCommand()),
    BYE(new ByeCommand()),
    UNKNOWN(null);
    private final Command command;
    // Enum constructor
    CommandTypes(Command command) {
        this.command = command;
    }

    // Getter for command
    public Command getCommand() {
        return command;
    }

    // Getter for regex
    public String getRegex() {
        return this.command.getRegex();
    }

    // Static method to find the matching command by regex
    public static CommandTypes findMatchingCommand(String userInput) {
        for (CommandTypes commandType : CommandTypes.values()) {
            if (commandType == CommandTypes.UNKNOWN) {
                break;
            } else if (Pattern.matches(commandType.getRegex(), userInput)) {
                return commandType;
            }
        }
        return CommandTypes.UNKNOWN;
    }
}