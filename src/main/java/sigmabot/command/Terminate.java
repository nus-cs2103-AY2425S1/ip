package sigmabot.command;

import sigmabot.ui.UiComponent;

public class Terminate extends Command {
    @Override
    public void execute(UiComponent ui) {
        System.out.println("Bye");
        ui.closeScanner();
    }
}
