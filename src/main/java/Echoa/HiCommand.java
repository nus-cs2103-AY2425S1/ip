package echoa;

public class HiCommand extends Command {
    public HiCommand(Ui ui, Parser parser, TaskList taskList, Storage storage) {
        super(ui, parser, taskList, storage);
    }

    @Override
    public void execute(String line) {
        // do nothing
    }

    @Override
    public String getMessage() {
        return ui.getStartMessage();
    }
}
