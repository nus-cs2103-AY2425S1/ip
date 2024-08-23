package bobbybot.commands;

import bobbybot.Storage;
import bobbybot.TaskList;
import bobbybot.ui.Ui;

public class CommandBye extends Command {
    public CommandBye(String argument) {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        setExit();
        ui.printInput("Bye. Hope to see you again soon!");
    }
}
