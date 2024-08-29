package Denim.Commands;

import Denim.Exceptions.DenimException;
import Denim.TaskList;
import Denim.Storage.TaskIO;
import Denim.Tasks.Task;

public class InvalidCommand extends Command {
    private String error;
    private String feedback;

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
