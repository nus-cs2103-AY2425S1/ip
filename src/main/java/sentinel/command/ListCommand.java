package sentinel.command;

import sentinel.ui.Ui;
import sentinel.utils.SentinelList;

public class ListCommand extends Command {
    public ListCommand(Ui ui, SentinelList list) {
        super(ui, list);
    }

    @Override
    public void execute(String input) {
        ui.showList(list);
    }
}