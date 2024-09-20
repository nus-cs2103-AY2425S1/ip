package bing.command;

import bing.ui.*;
import bing.storage.*;
import bing.task.*;

public class StatsCommand implements Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showStatistics(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
