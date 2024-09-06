package derek.command;
import derek.Ui;

public class ConsentCommand extends Command {

    public ConsentCommand(String command) {
        super(command);
    }

    public String execute(Ui ui) {
        return ui.getUserName();
    }
}
