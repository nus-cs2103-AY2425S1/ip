package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

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
