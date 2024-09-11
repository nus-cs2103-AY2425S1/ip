package milo.command;

import milo.tasks.TaskList;
import milo.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList) {
        return;
    }

    @Override
    public String commandToString(Ui ui, TaskList taskList) {
        return ui.printList(taskList);
    }
}
