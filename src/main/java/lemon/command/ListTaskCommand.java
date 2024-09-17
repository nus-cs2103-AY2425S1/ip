package lemon.command;

import lemon.Lemon;

public class ListTaskCommand extends Command {
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
