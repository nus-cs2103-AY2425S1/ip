package taskon.commands;

import taskon.storage.Storage;
import taskon.task.TaskList;
import taskon.ui.Ui;

public class OnCommand extends Command {
    public static final String COMMAND_WORD = "on";
    private String date;
    public OnCommand(String date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTasksOnDate(date, taskList);
    }
}
