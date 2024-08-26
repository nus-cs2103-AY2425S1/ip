package thanos.commands;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.TaskList;
import thanos.ui.Ui;

public abstract class Command {
    private final String argument;

    public Command(String argument) {
        this.argument = argument;
    }

    public String getArgument() {
        return this.argument;
    }

    public abstract void execute(TaskList taskList, Ui ui) throws InvalidCommandException;

    public boolean isExit() {
        return false;
    }
}
