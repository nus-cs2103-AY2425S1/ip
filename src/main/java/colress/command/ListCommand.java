package colress.command;

import colress.TaskList;
import colress.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super("");
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.printTasks(taskList, "list");
    }
}
