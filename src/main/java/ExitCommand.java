public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.writeToFile(tasks.formatTasksToSave());
        ui.exitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
