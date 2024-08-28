package oyster.commands;

import oyster.LogicController;
import oyster.Oyster;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("See you again! " + Oyster.CHATBOT_EMOJI);
    }

    @Override
    public void execute() {
        LogicController.close();
    }
}
