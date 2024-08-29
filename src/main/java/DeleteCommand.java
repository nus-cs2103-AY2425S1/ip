public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KronaException {
        try {
            Task task = tasks.get(taskIndex);
            tasks.deleteTask(taskIndex);
            ui.showMessage("Noted. I've removed this task:");
            ui.showMessage(task.toString());
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
            storage.save(tasks);  // Save tasks after deleting
        } catch (IndexOutOfBoundsException e) {
            throw new KronaException("Task index is out of bounds.");
        }
    }
}