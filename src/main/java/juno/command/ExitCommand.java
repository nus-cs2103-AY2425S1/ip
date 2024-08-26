package juno.command;

import juno.ui.JunoUi;


public class ExitCommand extends Command {
    JunoUi junoUi;
    public ExitCommand(JunoUi junoUi) {
        this.junoUi = junoUi;
    }
    @Override
    public void runCommand() {
        this.junoUi.displayFarewellMessage();
    }

    @Override
    public boolean isInWhileLoop() {
        return false;
    }
}
