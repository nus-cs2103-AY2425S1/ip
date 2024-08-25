import java.util.HashMap;

public class EmptyCommand extends Command {

    public EmptyCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("""
                That's a blank. I can't help you if you don't say anything.""");
    }

}
