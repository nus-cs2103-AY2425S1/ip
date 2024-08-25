package calebyyy.commands;

import calebyyy.Calebyyy;
import calebyyy.TaskList;
import calebyyy.Ui;

public class ByeCommand extends Command {
    public ByeCommand(Calebyyy calebyyy, Ui ui, TaskList taskList) {
        super(calebyyy, ui, taskList);
    }

    @Override
    public void execute(String input) {
        ui.byeMessage();
        System.exit(0);
    }
}