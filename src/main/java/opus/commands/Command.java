package opus.commands;

import opus.exceptions.OpusException;
import opus.Storage;
import opus.TaskList;

/**
 * Represents a command that can be executed by the user.
 */
public interface Command {
    String execute(TaskList taskList, Storage storage) throws OpusException;
    boolean isExit();
}
