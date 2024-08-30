public class DeleteCommand extends Command {

    private int taskIndex;

    public DeleteCommand (int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        tasks.deleteTask(taskIndex);
        Task t = tasks.getTasks().get(taskIndex);
        ui.displayDeleteTask(t, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
