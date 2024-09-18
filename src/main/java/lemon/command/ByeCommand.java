package lemon.command;

import lemon.Lemon;

public class ByeCommand extends Command{
    public ByeCommand(CommandType ct) {
        super(ct);
    }
    @Override
    public void run(Lemon lemonInstance) {
        lemonInstance.getUi().printEndingMsg();
        lemonInstance.stop();
    }
}
