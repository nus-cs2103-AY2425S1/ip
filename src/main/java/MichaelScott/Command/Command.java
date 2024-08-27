package MichaelScott.Command;

import MichaelScott.Exception.MichaelScottException;
import MichaelScott.Task.TaskList;

public interface Command {

    String execute(TaskList tasks) throws MichaelScottException;
    default boolean isExit() {
        return false;
    }
}
