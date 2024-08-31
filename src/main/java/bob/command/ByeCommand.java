package bob.command;

import bob.tasks.TaskList;

import bob.UI;

public class ByeCommand extends Command{

    public ByeCommand() {
        super(false);
    }

    public void execute(TaskList taskList) {
        UI.printExit();
    }
}
