package Johnson.command;

import Johnson.utils.Utilities;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    public static boolean isRunning = true;

    private static final String COMMAND_MSG = "Signing off for now, Chief.\nRadio in when you need me.";
    public static final String COMMAND_WORD = "quit";

    public ExitCommand() {
    }

    @Override
    public String executeCommand() {
        Utilities.OutlineMessage(COMMAND_MSG);
        isRunning = false;
        return COMMAND_MSG;
    }
}
