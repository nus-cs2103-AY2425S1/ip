package oyster.commands;

import oyster.LogicController;
import oyster.Oyster;

public class ExitCommand extends Command {
    /**
     * Creates an ExitCommand to exit the chatbot.
     */
    public ExitCommand() {
        super("See you again! " + Oyster.CHATBOT_EMOJI);
    }

    /**
     * Closes the chatbot when executed.
     */
    @Override
    public void execute() {
        LogicController.close();
    }
}
