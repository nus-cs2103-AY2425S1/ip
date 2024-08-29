public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    protected void execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException {
        taskList.addTask(task);
        storage.toFile(taskList);
        ui.printCount();
    }
}
