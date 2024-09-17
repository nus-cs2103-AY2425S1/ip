package echoa;

public class ByeCommand extends Command {
    public ByeCommand(Ui ui, Parser parser, TaskList taskList, Storage storage) {
        super(ui, parser, taskList, storage);
    }

    @Override
    public void execute(String line) {
        // do nothing
    }

    @Override
    public String getMessage() {
        return ui.getEndMessage();
    }
}
