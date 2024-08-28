package jude.command;

import jude.Storage;
import jude.TaskList;
import jude.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        exit();
    }

    @Override
    public String toString() {
        return "ExitCommand";
    }
}
