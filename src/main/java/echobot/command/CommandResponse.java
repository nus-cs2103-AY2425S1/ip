package echobot.command;

import echobot.command.Command.CommandType;

/**
 * Represents a command response.
 */
public class CommandResponse {
    private final CommandType commandType;
    private final String response;

    public CommandResponse(CommandType commandType, String response) {
        this.commandType = commandType;
        this.response = response;
    }

    public String getResponse() {
        return this.response;
    }

    public CommandType getCommandType() {
        return this.commandType;
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return True if the command is an exit command, otherwise false.
     */
    public boolean isExitCommand() {
        return this.commandType.equals(CommandType.EXIT);
    }
}
