package sentinel.command;

import sentinel.ui.Ui;
import sentinel.utils.Parser;
import sentinel.utils.SentinelList;
import sentinel.Sentinel;

public class ModifyCommand extends Command {
    private final Sentinel.CommandType commandType;

    public ModifyCommand(Ui ui, SentinelList list, Sentinel.CommandType commandType) {
        super(ui, list);
        this.commandType = commandType;
    }

    @Override
    public void execute(String input) {
        int num;
        try {
            num = Parser.parseIndex(input);
        } catch (Exception e){
            ui.showError(e);
            return;
        }
        if (num > list.size()) {
            ui.showNoItemExists();
            return;
        } else if (num <= 0) {
            ui.showModifyGuidelines();
            return;
        }
        switch (commandType) {
            case delete -> ui.showRemovedAndRemaining(list, list.remove(num - 1));
            case mark -> toggleTaskMark(num - 1, true);
            case unmark -> toggleTaskMark(num - 1, false);
        }
    }

    private void toggleTaskMark(int index, boolean mark) {
        if (mark == list.taskIsDone(index)) {
            ui.showAlreadyMarked(list.get(index));
        } else {
            list.toggleMark(index);
            ui.showTaskMark(list.get(index));
        }
    }
}

