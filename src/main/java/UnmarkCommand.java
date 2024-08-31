public class UnmarkCommand extends Command {
    int selection;

    public UnmarkCommand(int selection) {}

    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        tasklist.unMark(selection);
        try {
            storage.save(tasklist.getTaskList());
            output.append("OK, I've marked this task as not done yet:").append("\n").append(tasklist.get(selection).toString());
            ui.printString(output.toString());
        } catch (AsuraException e) {
            ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
