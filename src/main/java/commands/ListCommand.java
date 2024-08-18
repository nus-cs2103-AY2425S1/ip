package commands;

import common.Command;
import common.Ui;
import storage.TaskStorage;

public class ListCommand extends Command {
    @Override
    public boolean execute(Ui ui, TaskStorage storage) {
        StringBuilder list = new StringBuilder();
        int index = 1;

        for (String task : storage.getTasks()) {
            list.append(index).append(". ").append(task).append("\n");
            index++;
        }

        ui.printMessage("\n" + list.toString());
        return true;
    }
}