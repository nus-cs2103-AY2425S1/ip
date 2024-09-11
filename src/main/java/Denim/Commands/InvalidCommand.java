package denim.commands;

import denim.TaskList;
import denim.storage.WriteTaskFile;

/**
 * Represents an invalid command that can be executed.
 */
public class InvalidCommand extends Command {
    private final String error;
    private final String feedback;

    /**
     * Constructs a new invalid command with the specified description.
     * This command is constructed whenver the parser is unable to determine the command to execute.
     *
     * @param error The description of the error.
     * @param feedback any feedback that could be useful to resolve this error.
     */
    public InvalidCommand(String error, String feedback) {
        this.error = error;
        this.feedback = feedback;
    }


    @Override
    public CommandResult execute(TaskList taskList, WriteTaskFile writeTaskFile) {
        String returnMessage = String.format("%s\n%s", error, feedback);
        return new CommandResult(returnMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
