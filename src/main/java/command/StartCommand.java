package command;

import task.TaskManager;
import ui.Ui;

public class StartCommand extends Command {

    private static final String START_MSG = "Hello! I'm %s\nWhat can I do for you?";

    private String name;

    public StartCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.send(String.format(START_MSG, this.name));
    }
}
