package bro.command;

import bro.ui.UI;

public class ExitCommand implements Command {

    public void execute(UI ui) {
        ui.showGoodbye();
    }

    public boolean isExit() {
        return true;
    }
}
