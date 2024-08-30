package sentinel.command;

import sentinel.ui.Ui;
import sentinel.utils.SentinelList;

public class ByeCommand extends Command {
    public ByeCommand(Ui ui, SentinelList list) {
        super(ui, list);
    }

    @Override
    public void execute(String input) {
        ui.showGoodbye();
    }
}
