public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws StorageIOException {
        storage.save(tasks);
        ui.showExitMessage();
    }

}
