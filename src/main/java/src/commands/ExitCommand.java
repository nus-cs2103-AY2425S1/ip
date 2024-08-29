package src.commands;

import src.Storage;
import src.TaskList;
import src.Ui;
import src.commands.Command;

public class ExitCommand extends Command {

    public ExitCommand(boolean isActive, String input) {
        super(isActive, input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }
}
