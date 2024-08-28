package rotodo.commands;

import rotodo.exception.InvalidInputException;
import rotodo.processes.Storage;
import rotodo.processes.Ui;
import rotodo.tasklist.TaskList;

public class DeleteCommand extends Command {
    int idx;

    public DeleteCommand(int i) {
        idx = i;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage st) {
        try {
            ui.addMessage(tl.deleteTask(idx));
        } catch (InvalidInputException e) {
            ui.addMessage(e.toString());
        }
    }
    
}
