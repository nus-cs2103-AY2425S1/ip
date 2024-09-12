package bob;

public class UndoCommand extends Command {
    protected static String[] params = new String[] {
            "undo"
    };
    protected static int paramCount = 0;

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
