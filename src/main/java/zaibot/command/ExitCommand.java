package zaibot.command;

import zaibot.Storage;
import zaibot.TaskList;
import zaibot.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("bye", null);
    }

    @Override
    public void runCommandSpecificLogic(TaskList tasks, Storage storage) {
        Ui.printMessage("GOODBYE");
        super.setWillContinueLoop(false);
    }
}
