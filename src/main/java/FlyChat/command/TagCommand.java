package flychat.command;

import java.util.InputMismatchException;

import flychat.core.Parser;
import flychat.core.TaskList;
import flychat.core.Ui;

public class TagCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Parser parser, String inputString) {
        try {
            return ui.announceString(taskList.tag(parser.getTargetTaskIndex(inputString),
                    parser.getTags(inputString)));
        } catch (InputMismatchException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return ui.announceString("Please ensure that you typed the correct task number");
        } catch (IllegalArgumentException e) {
            return ui.announceString(e.getMessage());
        }
    }
}
