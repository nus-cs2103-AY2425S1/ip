public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KronaException {
        try {
            Task task = tasks.get(taskIndex);
            task.markNotDone();
            ui.showMessage("OK, I've marked this task as not done yet:");
            ui.showMessage(task.toString());
            storage.save(tasks);  
        } catch (IndexOutOfBoundsException e) {
            throw new KronaException("Task index is out of bounds.");
        }
    }
}