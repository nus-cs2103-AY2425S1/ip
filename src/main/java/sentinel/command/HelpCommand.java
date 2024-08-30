package sentinel.command;

import sentinel.ui.Ui;
import sentinel.utils.SentinelList;

public class HelpCommand extends Command {
    public HelpCommand(Ui ui, SentinelList list) {
        super(ui, list);
    }

    @Override
    public void execute(String input) {
        ui.showHelp();
    }
}
