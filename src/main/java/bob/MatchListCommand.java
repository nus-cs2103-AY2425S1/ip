package bob;

public class MatchListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("Did you mean 'list'?");
    }
}

