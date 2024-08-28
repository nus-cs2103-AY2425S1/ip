package ned;

import ned.commands.*;

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

    /**
     * Constructs an instance which stores an instance of every class that implements the Command interface
     * @param command The command subclass which is associated with the enum constant
     */
    CommandTypes(Command command) {
        this.command = command;
    }

    /**
     * Getter method to return the command instance associated with each command type
     * @return Command subclass
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Returns a CommandTypes object by parsing the user input and matching it to each Command subclass's associated
     * regex. The CommandTypes are a fixed set of values.
     * @param userInput String representing a line of user input
     * @return A CommandTypes object, with its associated Command subclass
     */
    public static CommandTypes findMatchingCommand(String userInput) {
        for (CommandTypes commandType : CommandTypes.values()) {
            if (commandType == CommandTypes.UNKNOWN) {
                break;
            } else if (Pattern.matches(commandType.getCommand().getRegex(), userInput)) {
                return commandType;
            }
        }
        return CommandTypes.UNKNOWN;
    }
}