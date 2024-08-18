package calebyyy.commands;

import calebyyy.Calebyyy;

public class ListCommand extends Command {
    public ListCommand(Calebyyy calebyyy) {
        super(calebyyy);
    }

    @Override
    public void execute(String input) {
        calebyyy.listTasks();
    }
}