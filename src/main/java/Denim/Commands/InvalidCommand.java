package denim.commands;

import denim.TaskList;
import denim.storage.TaskIO;

public class InvalidCommand extends Command {
    private final String error;
    private final String feedback;

    public InvalidCommand(String error, String feedback) {
        this.error = error;
        this.feedback = feedback;
    }
    @Override
    public CommandResult execute(TaskList taskList, TaskIO taskIO) {
        String returnMessage = String.format("%s\n%s", error, feedback);
        return new CommandResult(returnMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
