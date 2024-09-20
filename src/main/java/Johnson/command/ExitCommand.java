package Johnson.command;

import Johnson.utils.Utilities;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    /**
     * The command word that identifies an ExitCommand instance.
     */
    public static final String COMMAND_WORD = "quit";

    /**
     * The message that is displayed when an ExitCommand instance is executed successfully.
     */
    public static final String COMMAND_MSG = "Signing off for now, Chief.\nRadio in when you need me.";


    public ExitCommand() {
    }

    @Override
    public String executeCommand() {
        Utilities.OutlineMessage(COMMAND_MSG);
        return COMMAND_MSG;
    }
}
