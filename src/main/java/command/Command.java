package command;

import tasklist.TaskList;

import ui.CommandLineUI;

public abstract class Command {
    public abstract void execute(TaskList tasklist, CommandLineUI ui);

    public abstract boolean isExit();
}
