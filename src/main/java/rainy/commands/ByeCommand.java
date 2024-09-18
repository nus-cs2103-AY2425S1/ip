package rainy.commands;

import rainy.tasks.TaskTracker;

/**
 * Executes the instruction based on "bye" user input, and prints out the appropriate line.
 */
public class ByeCommand extends Command {
    public ByeCommand() {

    }

    public TaskTracker getResponse() {
        this.ui.goodbyeMessage();
        return new TaskTracker();
    }
}
