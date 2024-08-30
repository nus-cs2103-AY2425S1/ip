package sigmabot.command;

import sigmabot.ui.UiComponent;

public class Joke extends Command {
    @Override
    public void execute(UiComponent ui) {
        ui.printDialogue("tell joke");
    }
}
