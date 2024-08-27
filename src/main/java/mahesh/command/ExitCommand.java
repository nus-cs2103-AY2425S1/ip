package mahesh.command;

import mahesh.util.Ui;

public class ExitCommand extends Command {

    public void execute() {
        Ui.printExitMessage();
    }

    public boolean isExit() {
        return true;
    }

}
