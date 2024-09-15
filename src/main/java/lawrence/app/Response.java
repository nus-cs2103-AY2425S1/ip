package lawrence.app;

import lawrence.command.CommandType;

/**
 * Contains information about the action taken by the bot after issuing a command to it.
 */
public record Response(CommandType commandType, String message, boolean shouldContinue) {
}
