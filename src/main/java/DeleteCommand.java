public class DeleteCommand extends Command {

    private final int index;

    DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        ui.showDeleteTask(tasks.deleteTask(this.index), tasks.getSize());
    }
}
