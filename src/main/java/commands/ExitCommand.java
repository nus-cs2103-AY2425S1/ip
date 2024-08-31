package main.java.commands;

import main.java.tasks.TaskList;
import main.java.util.Ui;

public class ExitCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.sayGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
