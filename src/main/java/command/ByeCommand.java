package command;

import tasklist.TaskList;
import exception.ParserException;
import ui.Ui;

public class ByeCommand implements Command {
    public ByeCommand(String cmdline) throws ParserException {
        if (!cmdline.equals("bye")) {
            throw new ParserException("Too much arguments for bye command");
        }
    } 

    public void execute(TaskList tasks, Ui ui) {
        // do nothing
    }

    public boolean isExit() { return true; }
}
