public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        storage.save(tasklist);
        ui.sayGoodbyeMessage();
    }
}
