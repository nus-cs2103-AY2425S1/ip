package command;

import exception.ParserException;
import tasklist.TaskList;
import ui.Ui;

public class ListCommand implements Command {
    public ListCommand(String cmdline) throws ParserException {
        if (!cmdline.equals("list")) {
            throw new ParserException("Too much arguments for list command");
        }
    }

    public void execute(TaskList tasks, Ui ui) {
        ui.println("The tasks list as follow:");
        for (int i = 0; i < tasks.size(); ++i) {
            ui.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public boolean isExit() { return false; }
}
