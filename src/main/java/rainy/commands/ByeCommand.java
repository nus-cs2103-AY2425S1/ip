package rainy.commands;

import rainy.tasks.TaskTracker;

public class ByeCommand extends Command {
    public ByeCommand() {

    }

    public TaskTracker getResponse() {
        this.ui.goodbyeMessage();
        return new TaskTracker();
    }
}