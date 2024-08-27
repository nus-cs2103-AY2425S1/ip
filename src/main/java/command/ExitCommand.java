package command;

import task.TaskManager;
import ui.Ui;

public class ExitCommand extends Command {
    public static final String CMD_WORD = "bye";
    private static final String END_MSG = "Bye. Hope to see you again soon!";

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.send(END_MSG);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
