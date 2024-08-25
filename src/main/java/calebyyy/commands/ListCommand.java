package calebyyy.commands;

import calebyyy.Calebyyy;
import calebyyy.TaskList;
import calebyyy.Ui;

public class ListCommand extends Command {
    public ListCommand(Calebyyy calebyyy, Ui ui, TaskList taskList) {
        super(calebyyy, ui, taskList);
    }

    @Override
    public void execute(String input) {
        taskList.listTasks();
    }
}