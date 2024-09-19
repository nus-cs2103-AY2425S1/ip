package lemon.command;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import lemon.Lemon;

/**
 * Represent the {@link Command} for undoing the previous command
 * @author He Yiheng
 */
public class HelpCommand extends Command {
    /**
     * Constructor for UndoCommand
     * @param ct stores the enum {@link CommandType}
     */
    public HelpCommand(CommandType ct) {
        super(ct);
    }

    @Override
    public void run(Lemon lemonInstance) {
        StringBuilder helpMsgBuilder = new StringBuilder();
        ArrayList<CommandType> commands = new ArrayList<>(EnumSet.allOf(CommandType.class));
        for (CommandType command : commands) {
            helpMsgBuilder.append(command.toString());
        }

        lemonInstance.getUi().printHelpPage(helpMsgBuilder.toString());
    }
}
