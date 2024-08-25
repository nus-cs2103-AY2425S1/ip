import java.util.HashMap;

public class UnknownCommand extends Command {

    public UnknownCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("\nNo idea what that means. Try again.");
    }
}
