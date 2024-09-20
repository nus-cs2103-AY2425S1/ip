package flychat.command;

import flychat.core.Parser;
import flychat.core.TaskList;
import flychat.core.Ui;

public class FindCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Parser parser, String inputString) {
        try {
            return ui.announceString(taskList.find(parser.getKeyWord(inputString)));
        } catch (IllegalArgumentException e) {
            return ui.announceString(e.getMessage());
        }
    }
}
