public class DeleteCommand extends Command{

    private int itemIdx;

    public DeleteCommand(int itemIdx) {
        this.itemIdx = itemIdx;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = tasks.delete(this.itemIdx);
        ui.echo(message);
    }

    public boolean isExit() {
        return false;
    }
}
