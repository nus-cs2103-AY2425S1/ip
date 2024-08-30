package michaelscott.command;

import michaelscott.utils.MichaelScottException;
import michaelscott.task.TaskList;

public interface Command {

    String execute(TaskList tasks) throws MichaelScottException;

    default boolean isExit() {
        return false;
    }
}
