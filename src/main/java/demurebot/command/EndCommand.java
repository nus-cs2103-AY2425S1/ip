package demurebot.command;

import demurebot.task.TaskList;
import demurebot.ui.Ui;

public class EndCommand extends Command{
    public EndCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList list, Ui ui) {
        ui.displayEnd();
    }
}
