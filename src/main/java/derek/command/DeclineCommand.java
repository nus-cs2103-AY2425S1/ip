package derek.command;
import derek.Ui;

public class DeclineCommand extends Command {
    private Ui ui;
    public DeclineCommand(String command, Ui ui) {
        super(command);
        this.ui = ui;
    }

    @Override
    public String execute() {
        return ui.printLeavingMessage();
    }
}
