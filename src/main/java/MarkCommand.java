public class MarkCommand extends Command {
    int selection;

    public MarkCommand(int selection) {
        this.selection = selection;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        tasklist.get(selection).markAsDone();
        try {
            storage.save(tasklist.getTaskList());
            output.append("Nice! I've marked this task as done:").append("\n").append(tasklist.get(selection).toString());
            ui.printString(output.toString());
        } catch (AsuraException e) {
            ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
