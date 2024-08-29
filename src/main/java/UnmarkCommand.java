public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CharlotteException {
        if (index < 1 || index > tasks.getSize()) {
            throw new CharlotteException("Task number is invalid. Please try again");
        }

        Task task = tasks.getTask(index - 1);
        task.unmark();
        ui.showMessage("OK, I've marked this task as not done yet:\n  " + task);
        storage.saveTasks(tasks);
    }
}
