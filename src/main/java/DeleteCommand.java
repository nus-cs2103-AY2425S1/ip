public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        super(CommandType.DELETE);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonoBotException {
        Task deletedTask = tasks.getTask(index);
        tasks.deleteTask(index);
        ui.printDeletedTask(deletedTask, tasks.size());
        storage.save(tasks.getTasks());
    }
}
