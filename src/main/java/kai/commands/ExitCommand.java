package kai.commands;

import kai.Ui;

/**
 * This Command causes the chatbot to exit
 */
public class ExitCommand extends Command {

    @Override
    public void invoke(Ui ui) {
        ui.exit();
    }
}
