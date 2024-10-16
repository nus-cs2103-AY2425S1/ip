package michaelscott.command;

import michaelscott.task.TaskList;
import michaelscott.utils.MichaelScottException;

/**
 * This represents the interface for a command
 */
public interface Command {

    String execute(TaskList tasks) throws MichaelScottException;

    String getSimpleName();
    default boolean isExit() {
        return false;
    }
}
