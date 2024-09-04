package lawrence.command;

import lawrence.database.TaskFileManager;
import lawrence.task.TaskList;
import lawrence.ui.UserInterface;

public class ExitSessionCommand extends Command {
    public ExitSessionCommand() {
       // Empty constructor
    }
    @Override
    public void execute(TaskList tasks, TaskFileManager manager, UserInterface ui) {
        ui.showExitMessage();
    }

    @Override
    public boolean shouldContinue() {
        return false;
    }
}
