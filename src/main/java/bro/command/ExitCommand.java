package bro.command;

import bro.ui.UI;

public class ExitCommand implements Command {

    public String execute(UI ui) {
        return ui.showGoodbye();
    }

    public boolean isExit() {
        return true;
    }
}
