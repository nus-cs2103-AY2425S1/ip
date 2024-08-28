package oyster.commands;

import oyster.LogicController;
import oyster.Oyster;

public class ByeCommand extends Command {
    public ByeCommand() {
        super("See you again! " + Oyster.CHATBOT_EMOJI);
    }

    @Override
    public void execute() {
        LogicController.close();
    }
}
