package flychat.command;

import java.util.InputMismatchException;

import flychat.core.Parser;
import flychat.core.TaskList;
import flychat.core.Ui;

public class DeleteCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Parser parser, String inputString) {
        try {
            return ui.announceString(taskList.deleteTask(parser.getTargetTaskIndex(inputString)));
        } catch (IndexOutOfBoundsException e) {
            return ui.announceString("Please ensure that you typed the correct task number");
        } catch (InputMismatchException e) {
            return ui.announceString("Please ensure that you typed a valid task number");
        }
    }
}