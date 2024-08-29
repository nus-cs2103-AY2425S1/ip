public class UnmarkCommand extends Command {
    private int taskId;

    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.showMessage("Oh, having second thoughts? OK, I've marked that task as not done yet:\n\n\t"
                    + taskList.unmark(taskId) + "\n\nClearly, you're still undecided.");
            storage.save(taskList);
        } catch (Exception e) {
            // todo: handle exceptions
        }
    }
}
