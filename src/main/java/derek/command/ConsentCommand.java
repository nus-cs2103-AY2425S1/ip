package derek.command;
import derek.Storage;
import derek.Ui;

public class ConsentCommand extends Command {

    private Ui ui;
    public ConsentCommand(String command, Ui ui) {
        super(command);
        this.ui = ui;
    }

    @Override
    public String execute() {
        return this.ui.getUserName();
    }
}
