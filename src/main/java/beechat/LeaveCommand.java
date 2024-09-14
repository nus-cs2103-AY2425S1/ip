package beechat;

public class LeaveCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isLeave() {
        return true;
    }
}