package ned.commands;

import ned.Storage;
import ned.TaskList;
import ned.Ui;
import ned.exceptions.NedException;

public interface Command {
    void execute(TaskList taskList, Ui uiInstance, Storage storageInstance, String userInput) throws NedException;
    default boolean isExit() {
        return false;
    }
    String getRegex();
}
