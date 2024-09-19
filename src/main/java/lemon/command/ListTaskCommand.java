package lemon.command;

import lemon.Lemon;

/**
 * Represent the {@link Command} for listing all tasks
 * @author He Yiheng
 */
public class ListTaskCommand extends Command {
    /**
     * Constructor for ListTaskCommand
     * @param ct stores the enum {@link CommandType}
     */
    public ListTaskCommand(CommandType ct) {
        super(ct);
    }

    @Override
    public void run(Lemon lemonInstance) {
        if (lemonInstance.getTasks().size() == 0) {
            lemonInstance.getUi().printEmptyListMsg();
        } else {
            lemonInstance.getUi().printListMsg(lemonInstance.getTasks().toString());
        }
    }
}
