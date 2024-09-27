package zaibot.command;

import zaibot.utils.Storage;
import zaibot.utils.TaskList;
import zaibot.utils.Ui;

/**
 * This class is used to signify a command to exit from the application.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super("bye", null);
    }

    @Override
    public String runCommandSpecificLogic(TaskList tasks, Storage storage) {
        super.setWillContinueLoop(false);
        return Ui.printMessage("GOODBYE");
    }
}
