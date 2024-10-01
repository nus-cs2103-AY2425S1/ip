package flychat.command;

import flychat.core.Parser;
import flychat.core.TaskList;
import flychat.core.Ui;

public class UnmarkCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Parser parser, String inputString) {
        try {
            return ui.announceString(taskList.unmark(parser.getTargetTaskIndex(inputString)));
        } catch (IndexOutOfBoundsException e) {
            return ui.announceString("Please ensure that you typed the correct task number");
        }
    }
}