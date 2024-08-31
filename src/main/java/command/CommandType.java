package command;

import utility.Ui;
import exception.ElliotException;

public enum CommandType {
    LIST("list", new ListCommand()),
    MARK("mark", new MarkCommand()),
    UNMARK("unmark", new UnmarkCommand()),
    DELETE("delete", new DeleteCommand()),
    TODO("todo", new TodoCommand()),
    DEADLINE("deadline", new DeadlineCommand()),
    EVENT("event", new EventCommand()),
    BYE("bye", new ByeCommand());

    private final String commandString;
    private final Command command;

    CommandType(String commandString, Command command) {
        this.commandString = commandString;
        this.command = command;
    }

    public String getCommandString() {
        return commandString;
    }

    public Command getCommand() {
        return command;
    }

    public static CommandType parseStringToCommand(String commandString) throws ElliotException {
        for (CommandType type : CommandType.values()) {
            if (type.getCommandString().equalsIgnoreCase(commandString)) {
                return type;
            }
        }
        Ui.say("invalid command\n");
        throw new ElliotException();
    }
}
