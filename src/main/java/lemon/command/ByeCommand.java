package lemon.command;

import lemon.Lemon;

/**
 * Represent the {@link Command} for exiting lemon
 * @author He Yiheng
 */
public class ByeCommand extends Command {
    /**
     * Constructor for ByeCommand
     * @param ct stores the enum {@link CommandType} for troubleshooting
     */
    public ByeCommand(CommandType ct) {
        super(ct);
    }
    @Override
    public void run(Lemon lemonInstance) {
        lemonInstance.getUi().printEndingMsg();
        lemonInstance.stop();
    }
}
