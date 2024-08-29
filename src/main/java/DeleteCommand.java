public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks,Ui ui, Storage storage) {
        if (index >= tasks.getLength() || index < 0) {
            ui.showTaskNotFoundError();
            return;
        }
        tasks.removeTask(index);
        storage.overwriteFile(tasks);
    }
}
