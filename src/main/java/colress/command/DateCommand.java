package colress.command;

import colress.TaskList;
import colress.Ui;

public final class DateCommand extends ListCommand {
    public DateCommand() {
        super();
    }
    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.printTasks(taskList, "date");
    }
}
