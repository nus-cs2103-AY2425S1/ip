package derek.command;
import derek.Ui;

public class DeclineCommand extends Command {
    public DeclineCommand(String command) {
        super(command);
    }

    public String execute(Ui ui) {
        return ui.printLeavingMessage();
    }
}
