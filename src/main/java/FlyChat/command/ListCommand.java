package flychat.command;

import flychat.core.Parser;
import flychat.core.TaskList;
import flychat.core.Ui;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Parser parser, String inputString) {
        return ui.announceString(taskList.announceItems());
    }
}