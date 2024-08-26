public class ByeCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
        storage.save(tasks);
    }

    public boolean isExit() {
        return true;
    }
}
