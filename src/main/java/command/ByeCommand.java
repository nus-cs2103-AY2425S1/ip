package command;

import tasklist.TaskList;

import ui.CommandLineUI;

public class ByeCommand extends Command {
    protected int index;

    public void execute(TaskList tasklist, CommandLineUI ui) {
        // Do nth
    }

    public boolean isExit() {
        return true;
    }
}
