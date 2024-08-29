package src.commands;

import src.Storage;
import src.TaskList;
import src.Ui;

public class AddCommand extends Command {

    public AddCommand(boolean isActive, String input) {
        super(isActive, input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }
}
