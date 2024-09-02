public class AddDefaultCommand extends CommandBase {
    private final DefaultTask task;

    public AddDefaultCommand(DefaultTask task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlexException{
        tasks.addTask(task);
        storage.save(tasks.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
