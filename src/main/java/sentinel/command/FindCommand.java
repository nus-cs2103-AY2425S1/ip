package sentinel.command;

import sentinel.ui.Ui;
import sentinel.utils.Parser;
import sentinel.utils.SentinelList;

public class FindCommand extends Command{
    public FindCommand(Ui ui, SentinelList list){
        super(ui, list);
    }

    @Override
    public void execute(String input) {
        String keyword = Parser.parseKeyword(input);
        ui.showFileredList(list, keyword);
    }
}
