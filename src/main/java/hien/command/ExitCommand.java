package hien.command;

import hien.exception.HienException;
import hien.main.Storage;
import hien.main.TaskList;
import hien.ui.UI;

public class ExitCommand extends Command {
    public ExitCommand(boolean isExit) {
        super(isExit);
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws HienException {
        String goodbyeMessage = "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!";
        return goodbyeMessage;

    }

}
