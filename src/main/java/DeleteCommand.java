public class DeleteCommand extends Command {

    // DeleteCommand will have a task index to delete
    private int toDel;

    public DeleteCommand(String toDel) {
        this.toDel = Integer.parseInt(toDel);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ConverSageException {
        Task toDelTask = tasks.getTask(this.toDel - 1);
        tasks.deleteTask(toDel);
        ui.showLine();
        ui.showMessage("Noted, I've removed this task:");
        ui.showMessage("  " + toDelTask.toString());
        ui.showMessage("You have " + tasks.size() + " tasks in your list.");
        storage.save(tasks.getTasks());
    }
}
