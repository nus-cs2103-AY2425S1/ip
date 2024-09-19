package commands;

import bob.ExecutionStack;
import bob.Storage;
import bob.TaskList;
import bob.Ui;

public class UndoCommand extends Command {
    public static String[] params = new String[] {
            "undo"
    };
    public static int paramCount = 0;

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, ExecutionStack execStack) {
        if (execStack.isEmpty()) {
            return "No commands to undo.";
        }

        Command commandToUndo = execStack.pop();
        return commandToUndo.undo(tasks, ui, storage);
    }

    @Override
    public String undo(TaskList tasks, Ui ui, Storage storage) {
        // Do nothing
        return "";
    }
}
