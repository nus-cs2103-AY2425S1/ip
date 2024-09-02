public class AddTaskCommand extends Command {
    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpikeException {
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.getSize());
        storage.writeToFile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
