public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KronaException {
        try {
            Task task = tasks.get(taskIndex);
            task.markDone();
            ui.showMessage("Nice! I've marked this task as done:");
            ui.showMessage(task.toString());
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new KronaException("Task index is out of bounds.");
        }
    }
}