package rotodo.commands;

import rotodo.exception.InvalidInputException;
import rotodo.processes.Storage;
import rotodo.processes.Ui;
import rotodo.tasklist.TaskList;

public class MarkCommand extends Command {
    int idx;
    boolean asStatus;

    public MarkCommand(int i, boolean as) {
        idx = i;
        asStatus = as;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage st) {
        try {
            if (asStatus) {
                ui.addMessage(tl.markDone(idx));
            } else {
                ui.addMessage(tl.unmarkDone(idx));
            }
        } catch (InvalidInputException e) {
            ui.addMessage(e.toString());
        }
    }
}
