public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CharlotteException {
        if (index < 1 || index > tasks.getSize()) {
            throw new CharlotteException("Task number is invalid. Please try again");
        }

        Task task = tasks.getTask(index - 1);
        task.markAsDone();
        ui.showMessage("Nice! I've marked this task as done:\n " + task);
        storage.saveTasks(tasks);
    }
}
